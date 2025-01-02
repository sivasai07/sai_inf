package com.auth.Authentication.Repository;

import com.auth.Authentication.entity.EventResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventResultRepository extends JpaRepository<EventResult, Long> {
    Optional<EventResult> findByAthleteIdAndEventId(Integer athleteId, Integer eventId);
    List<EventResult> findByEventId(Integer eventId);
}

