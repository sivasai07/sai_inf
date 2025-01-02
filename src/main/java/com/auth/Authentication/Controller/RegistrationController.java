package com.auth.Authentication.Controller;

import com.auth.Authentication.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // Check the registration status of an athlete for an event
    @GetMapping("/{eventId}/athlete/{athleteId}/status")
    public String checkRegistrationStatus(@PathVariable Integer athleteId, @PathVariable Integer eventId) {
        return registrationService.checkRegistrationStatus(athleteId, eventId);
    }

    // Register an athlete for an event
    @PostMapping("/{eventId}/athlete/{athleteId}/register")
    public String registerAthleteForEvent(@PathVariable Integer athleteId, @PathVariable Integer eventId) {
        return registrationService.registerAthleteForEvent(athleteId, eventId);
    }
}
