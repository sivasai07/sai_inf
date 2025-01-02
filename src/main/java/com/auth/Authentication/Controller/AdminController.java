package com.auth.Authentication.Controller;

import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.entity.Coach;
import com.auth.Authentication.Services.AthleteService;
import com.auth.Authentication.Services.CoachService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    private final AthleteService athleteService;
    private final CoachService coachService;

    public AdminController(AthleteService athleteService, CoachService coachService) {
        this.athleteService = athleteService;
        this.coachService = coachService;
    }

    // Get all athletes
    @GetMapping("/athletes")
    public List<Athlete> getAllAthletes() {
        return athleteService.getAllAthletes();    }

    // Get all coaches
    @GetMapping("/coaches")
    public List<Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }
}
