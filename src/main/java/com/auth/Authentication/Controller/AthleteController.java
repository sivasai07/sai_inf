package com.auth.Authentication.Controller;

import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.Services.AthleteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/athletes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AthleteController {

    @Value("${upload.dir}")
    private String uploadDir;

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    // Get all athletes
    @GetMapping
    public List<Athlete> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    // Get a single athlete by ID
    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable Integer id) {
        return athleteService.getAthleteById(id);
    }
    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<Athlete> getAthleteByUserId(@PathVariable Integer userId) {
        return athleteService.getAthleteByUserId(userId);
    }
    @GetMapping("/getIdByUserId/{userId}")
    public ResponseEntity<?> getAthleteIdByUserId(@PathVariable Integer userId) {
        try {
            Integer athleteId = athleteService.getAthleteIdByUserId(userId);

            if (athleteId != null) {
                return ResponseEntity.ok(athleteId); // Sends the athleteId as plain JSON
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Athlete not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // Search for an athlete by username
    @GetMapping("/searchByUsername")
    public ResponseEntity<Athlete> getAthleteByUsername(@RequestParam String username) {
        return athleteService.getAthleteByUsername(username);
    }

    // Create a new athlete (with or without a coach)
    @PostMapping("/create")
    public Athlete createAthlete(@RequestBody Athlete athlete) {
        return athleteService.createAthlete(athlete);
    }

    // Update an existing athlete (but not the coach)
    @PutMapping("/update/{id}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable Integer id, @RequestBody Athlete athleteDetails) {
        return athleteService.updateAthlete(id, athleteDetails);
    }
    @PutMapping("/updateByUserId/{userId}")
    public ResponseEntity<Athlete> updateAthleteByUserId(@PathVariable Integer userId, @RequestBody Athlete athleteDetails) {
        return athleteService.updateAthleteByUserId(userId, athleteDetails);
    }
    // Update only the coach of an athlete
    @PutMapping("/updateCoach/{id}")
    public ResponseEntity<Athlete> updateCoach(@PathVariable Integer id, @RequestParam Integer coachId) {
        return athleteService.updateCoach(id, coachId);
    }

    // Delete an athlete by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Integer id) {
        return athleteService.deleteAthlete(id);
    }


    @PostMapping("/uploadProfilePicture")
    public ResponseEntity<String> uploadProfilePicture(
            @RequestParam("file") MultipartFile file,
            @RequestParam("athleteId") Integer athleteId) {

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
        }

        try {
            // Validate the file type (optional)
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Please upload an image.");
            }

            // Create a unique file name to prevent overwriting
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;

            // Save the file to the server
            File dest = new File(filePath);
            file.transferTo(dest);

            // Update the athlete's profile picture URL in the database
            String photoUrl = "/uploads/" + fileName;
            Athlete updatedAthlete = athleteService.updateAthleteProfilePicture(athleteId, photoUrl);

            if (updatedAthlete != null) {
                return ResponseEntity.ok("Profile picture uploaded successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Athlete not found");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file: " + e.getMessage());
        }
    }
}
