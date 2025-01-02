package com.auth.Authentication.Controller;

import com.auth.Authentication.Services.MeetService;
import com.auth.Authentication.entity.Meet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meets")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MeetController {

    private final MeetService meetService;

    public MeetController(MeetService meetService) {
        this.meetService = meetService;
    }

    // Create a new meet
    @PostMapping("/addMeet")
    public ResponseEntity<Meet> addMeet(@RequestBody Meet meet) {
        Meet createdMeet = meetService.createMeet(meet);
        return new ResponseEntity<>(createdMeet, HttpStatus.CREATED);
    }

    // Update an existing meet
    @PutMapping("/modifyMeet/{id}")
    public ResponseEntity<Meet> modifyMeet(@PathVariable Long id, @RequestBody Meet meet) {
        Meet updatedMeet = meetService.updateMeet(id, meet);
        return new ResponseEntity<>(updatedMeet, HttpStatus.OK);
    }

    // Delete a meet
    @DeleteMapping("/removeMeet/{id}")
    public ResponseEntity<Void> removeMeet(@PathVariable Long id) {
        meetService.deleteMeet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get a meet by ID
    @GetMapping("/findMeet/{id}")
    public ResponseEntity<Meet> findMeet(@PathVariable Long id) {
        Meet meet = meetService.getMeetById(id);
        return new ResponseEntity<>(meet, HttpStatus.OK);
    }

    // Get all meets
    @GetMapping("/listAllMeets")
    public ResponseEntity<List<Meet>> listAllMeets() {
        List<Meet> meets = meetService.getAllMeets();
        return new ResponseEntity<>(meets, HttpStatus.OK);
    }
}
