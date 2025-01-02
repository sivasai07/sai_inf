package com.auth.Authentication.Repository;

import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
    Optional<Coach> findByUsername(String username);

    // Custom method to find by username
    Optional<Coach> findByUser_Id(Integer userId);
    Optional<Coach> findByUserId(Integer userId);


}
