package com.auth.Authentication.Services;

import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.entity.Event;
import com.auth.Authentication.entity.Registration;
import com.auth.Authentication.Repository.AthleteRepository;
import com.auth.Authentication.Repository.EventRepository;
import com.auth.Authentication.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    // Check if an athlete is registered for an event
    public String checkRegistrationStatus(Integer athleteId, Integer eventId) {
        boolean isRegistered = registrationRepository.existsByAthleteIdAndEventId(athleteId, eventId);
        return isRegistered ? "Registered" : "Not Registered";
    }

    // Register an athlete for an event
    public String registerAthleteForEvent(Integer athleteId, Integer eventId) {
        // Check if already registered
        if (registrationRepository.existsByAthleteIdAndEventId(athleteId, eventId)) {
            return "Already Registered";
        }

        // Fetch athlete and event from the database
        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new RuntimeException("Athlete not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Create a new registration
        Registration registration = new Registration();
        registration.setAthlete(athlete);
        registration.setEvent(event);

        // Save the registration
        registrationRepository.save(registration);

        return "Successfully Registered for " + event.getEventTitle();
    }
}

