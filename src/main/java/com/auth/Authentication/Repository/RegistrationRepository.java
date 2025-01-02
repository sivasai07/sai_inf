package com.auth.Authentication.Repository;

import com.auth.Authentication.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Registration r WHERE r.athlete.athleteld = :athleteId AND r.event.eventId = :eventId")
    boolean existsByAthleteIdAndEventId(@Param("athleteId") Integer athleteId, @Param("eventId") Integer eventId);
}
