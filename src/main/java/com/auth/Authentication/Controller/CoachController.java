package com.auth.Authentication.Controller;

import com.auth.Authentication.entity.Coach;
import com.auth.Authentication.Services.CoachService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    // Get all coaches
    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    // Get a coach by ID
    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Integer id) {
        return coachService.getCoachById(id);
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<Coach> getCoachByUserId(@PathVariable Integer userId) {
        return coachService.getCoachByUserId(userId);
    }

    // Search for a coach by username
    @GetMapping("/searchByUsername")
    public ResponseEntity<Coach> getCoachByUsername(@RequestParam String username) {
        return coachService.getCoachByUsername(username);
    }

    // Update an existing coach
    @PutMapping("/update/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Integer id, @RequestBody Coach coachDetails) {
        return coachService.updateCoach(id, coachDetails);
    }
    @PutMapping("/updateByUserId/{userId}")
    public ResponseEntity<Coach> updateCoachByUserId(@PathVariable Integer userId, @RequestBody Coach coachDetails) {
        return coachService.updateCoachByUserId(userId, coachDetails);
    }

    // Delete a coach by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Integer id) {
        return coachService.deleteCoach(id);
    }
}
