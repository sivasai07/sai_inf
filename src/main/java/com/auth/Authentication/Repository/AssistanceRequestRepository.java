package com.auth.Authentication.Repository;

import com.auth.Authentication.entity.AssistanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssistanceRequestRepository extends JpaRepository<AssistanceRequest, Integer> {
    // Find a specific request between an athlete and a coach
    Optional<AssistanceRequest> findByAthleteIdAndCoachId(int athleteId, int coachId);

    // Get all requests for a coach
    List<AssistanceRequest> findByCoachId(int coachId);

    // Get all requests for an athlete
    List<AssistanceRequest> findByAthleteId(int athleteId);

}
