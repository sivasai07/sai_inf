package com.auth.Authentication.Services;

import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.entity.Coach;
import com.auth.Authentication.entity.User;
import com.auth.Authentication.Repository.AthleteRepository;
import com.auth.Authentication.Repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    @Autowired
    private final AthleteRepository athleteRepository;
    private final CoachRepository coachRepository;

    // Constructor to inject dependencies
    public AthleteService(AthleteRepository athleteRepository, CoachRepository coachRepository) {
        this.athleteRepository = athleteRepository;
        this.coachRepository = coachRepository;
    }

    public Integer getAthleteIdByUserId(Integer userId) {
        // Find the athlete by userId
        Athlete athlete = athleteRepository.findByUserId(userId);
        if (athlete != null) {
            return athlete.getId();  // Return athlete ID
        }
        return null;  // Return null if athlete is not found
    }
    // Create athlete for a user
    public void createAthleteForUser(User user) {
        Athlete athlete = new Athlete();

        // Set the user reference, not userld
        athlete.setUser(user); // Set the full User entity

        // Set additional fields as needed
        athlete.setUsername(user.getUsername());   // Use the username from the user table

        // Save the athlete entity
        athleteRepository.save(athlete);
    }

    // Get all athletes
    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    // Get athlete by ID
    public ResponseEntity<Athlete> getAthleteById(Integer id) {
        Optional<Athlete> athlete = athleteRepository.findById(id);
        return athlete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get athlete by username
    public ResponseEntity<Athlete> getAthleteByUsername(String username) {
        Optional<Athlete> athlete = athleteRepository.findByUsername(username);
        return athlete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new athlete (with or without a coach)
    public Athlete createAthlete(Athlete athlete) {
        if (athlete.getHeight() == null) {
            athlete.setHeight(0.0f); // Set a default height if not provided
        }

        if (athlete.getCoach() != null && athlete.getCoach().getCoachId() != 0) {
            // Fetch the Coach entity by coachId
            Coach coach = coachRepository.findById(athlete.getCoach().getCoachId())
                    .orElseThrow(() -> new IllegalArgumentException("Coach not found"));
            athlete.setCoach(coach); // Set the coach entity
        }

        // Save the athlete entity
        return athleteRepository.save(athlete);
    }

    // Update the coach of an athlete (admin only)
    public ResponseEntity<Athlete> updateCoach(Integer id, Integer coachId) {
        Optional<Athlete> athleteOptional = athleteRepository.findById(id);
        if (athleteOptional.isPresent()) {
            Athlete athlete = athleteOptional.get();

            // Fetch the coach by ID and set it to the athlete
            Coach coach = coachRepository.findById(coachId)
                    .orElseThrow(() -> new IllegalArgumentException("Coach not found"));
            athlete.setCoach(coach); // Update only the coach

            return ResponseEntity.ok(athleteRepository.save(athlete)); // Save the updated athlete
        }
        return ResponseEntity.notFound().build();
    }

    // Update athlete details, but do not update coach (coach update is separate)
    public ResponseEntity<Athlete> updateAthlete(Integer id, Athlete athleteDetails) {
        Optional<Athlete> athleteOptional = athleteRepository.findById(id);

        if (athleteOptional.isPresent()) {
            Athlete athlete = athleteOptional.get();

            // Update athlete fields only if they are provided
            if (athleteDetails.getFirstName() != null) athlete.setFirstName(athleteDetails.getFirstName());
            if (athleteDetails.getLastName() != null) athlete.setLastName(athleteDetails.getLastName());
            if (athleteDetails.getBirthDate() != null) athlete.setBirthDate(athleteDetails.getBirthDate());
            if (athleteDetails.getGender() != null) athlete.setGender(athleteDetails.getGender());
            if (athleteDetails.getHeight() != null) athlete.setHeight(athleteDetails.getHeight());
            if (athleteDetails.getWeight() != null) athlete.setWeight(athleteDetails.getWeight());
            if (athleteDetails.getCategory() != null) athlete.setCategory(athleteDetails.getCategory());
            if (athleteDetails.getPhotoUrl() != null) athlete.setPhotoUrl(athleteDetails.getPhotoUrl());

            athlete.setUpdatedAt(LocalDateTime.now()); // Update timestamp

            // Save and return updated athlete
            return ResponseEntity.ok(athleteRepository.save(athlete));
        }

        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Athlete> updateAthleteByUserId(Integer userId, Athlete athleteDetails) {
        // Find the athlete by userId
        Optional<Athlete> athleteOptional = athleteRepository.findByUser_Id(userId);

        if (athleteOptional.isPresent()) {
            Athlete athlete = athleteOptional.get();

            // Update only the provided fields
            if (athleteDetails.getFirstName() != null) athlete.setFirstName(athleteDetails.getFirstName());
            if (athleteDetails.getLastName() != null) athlete.setLastName(athleteDetails.getLastName());
            if (athleteDetails.getBirthDate() != null) athlete.setBirthDate(athleteDetails.getBirthDate());
            if (athleteDetails.getGender() != null) athlete.setGender(athleteDetails.getGender());
            if (athleteDetails.getHeight() != null) athlete.setHeight(athleteDetails.getHeight());
            if (athleteDetails.getWeight() != null) athlete.setWeight(athleteDetails.getWeight());
            if (athleteDetails.getCategory() != null) athlete.setCategory(athleteDetails.getCategory());
            if (athleteDetails.getPhotoUrl() != null) athlete.setPhotoUrl(athleteDetails.getPhotoUrl());

            athlete.setUpdatedAt(LocalDateTime.now()); // Update the timestamp

            // Save and return the updated athlete
            return ResponseEntity.ok(athleteRepository.save(athlete));
        }

        return ResponseEntity.notFound().build(); // If no athlete is found for the userId
    }

    public ResponseEntity<Athlete> getAthleteByUserId(Integer userId) {
        Optional<Athlete> athlete = athleteRepository.findByUser_Id(userId);
        return athlete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete an athlete by ID
    public ResponseEntity<Void> deleteAthlete(Integer id) {
        if (athleteRepository.existsById(id)) {
            athleteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Athlete updateAthleteProfilePicture(Integer athleteId, String photoUrl) {
        Athlete athlete = athleteRepository.findById(athleteId).orElse(null);
        if (athlete != null) {
            athlete.setPhotoUrl(photoUrl);
            return athleteRepository.save(athlete);
        }
        return null;
    }

    public List<Athlete> findAthletesByIds(List<Integer> athleteIds) {
        return athleteRepository.findAllById(athleteIds);
    }

}
