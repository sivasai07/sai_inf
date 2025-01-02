package com.auth.Authentication.Repository;

import com.auth.Authentication.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e FROM Event e WHERE :athleteId MEMBER OF e.registeredAthletes")
    List<Event> findEventsByRegisteredAthlete(@Param("athleteId") Integer athleteId);

    @Query("SELECT e FROM Event e WHERE :athleteId MEMBER OF e.acceptedAthletes")
    List<Event> findEventsByApprovedAthlete(@Param("athleteId") Integer athleteId);

}
