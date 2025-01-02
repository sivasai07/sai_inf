package com.auth.Authentication.Services;

import com.auth.Authentication.entity.Coach;
import com.auth.Authentication.entity.User;
import com.auth.Authentication.Repository.CoachRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final CoachRepository coachRepository;

    // Constructor for dependency injection
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    // Create a coach for a user
    public void createCoachForUser(User user) {
        Coach coach = new Coach();
        coach.setUser(user);  // Link the user entity
        coach.setUsername(user.getUsername()); // Use username from User entity
        // Save coach with default fields
        coachRepository.save(coach);
    }

    // Get all coaches
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }
    public ResponseEntity<Coach> getCoachByUserId(Integer userId) {
        Optional<Coach> coach = coachRepository.findByUserId(userId);
        return coach.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Get a coach by ID
    public ResponseEntity<Coach> getCoachById(Integer id) {
        Optional<Coach> coach = coachRepository.findById(id);
        return coach.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get a coach by username
    public ResponseEntity<Coach> getCoachByUsername(String username) {
        Optional<Coach> coach = coachRepository.findByUsername(username);
        return coach.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Update a coach's details
    public ResponseEntity<Coach> updateCoach(Integer id, Coach coachDetails) {
        Optional<Coach> coachOptional = coachRepository.findById(id);

        if (coachOptional.isPresent()) {
            Coach coach = coachOptional.get();
            // Update only provided fields
            if (coachDetails.getFirstName() != null) coach.setFirstName(coachDetails.getFirstName());
            if (coachDetails.getLastName() != null) coach.setLastName(coachDetails.getLastName());
            if (coachDetails.getBirthDate() != null) coach.setBirthDate(coachDetails.getBirthDate());
            if (coachDetails.getGender() != null) coach.setGender(coachDetails.getGender());
            if (coachDetails.getCategory() != null) coach.setCategory(coachDetails.getCategory());
            if (coachDetails.getPhotoUrl() != null) coach.setPhotoUrl(coachDetails.getPhotoUrl());

            coach.setUpdatedAt(LocalDate.now()); // Set the current date only


            return ResponseEntity.ok(coachRepository.save(coach));
        }

        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Coach> updateCoachByUserId(Integer userId, Coach coachDetails) {
        Optional<Coach> coachOptional = coachRepository.findByUser_Id(userId);

        if (coachOptional.isPresent()) {
            Coach coach = coachOptional.get();

            // Update only the provided fields
            if (coachDetails.getFirstName() != null) coach.setFirstName(coachDetails.getFirstName());
            if (coachDetails.getLastName() != null) coach.setLastName(coachDetails.getLastName());
            if (coachDetails.getBirthDate() != null) coach.setBirthDate(coachDetails.getBirthDate());
            if (coachDetails.getGender() != null) coach.setGender(coachDetails.getGender());
          //  if (coachDetails.getExperience() != null) coach.setExperience(coachDetails.getExperience());
            if (coachDetails.getCategory() != null) coach.setCategory(coachDetails.getCategory());
            if (coachDetails.getPhotoUrl() != null) coach.setPhotoUrl(coachDetails.getPhotoUrl());

            coach.setUpdatedAt(LocalDate.now()); // Update the timestamp

            // Save and return the updated coach
            return ResponseEntity.ok(coachRepository.save(coach));
        }

        return ResponseEntity.notFound().build(); // If no coach is found for the userId
    }

    // Delete a coach by ID
    public ResponseEntity<Void> deleteCoach(Integer id) {
        if (coachRepository.existsById(id)) {
            coachRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
