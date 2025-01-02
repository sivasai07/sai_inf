package com.auth.Authentication.Repository;

import com.auth.Authentication.entity.AssistantNotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistantNotifyRepository extends JpaRepository<AssistantNotify, Integer> {
    List<AssistantNotify> findByAthleteId(Integer athleteId);
    List<AssistantNotify> findByCoachId(Integer coachId);
}
