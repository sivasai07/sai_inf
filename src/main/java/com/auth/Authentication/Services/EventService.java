package com.auth.Authentication.Services;

import com.auth.Authentication.entity.Event;
import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.Repository.EventRepository;
import com.auth.Authentication.Repository.AthleteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AthleteRepository athleteRepository;

    public EventService(EventRepository eventRepository, AthleteRepository athleteRepository) {
        this.eventRepository = eventRepository;
        this.athleteRepository = athleteRepository;
    }

    // Create an event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Update an event
    public Event updateEvent(Integer eventId, Event updatedEvent) {
        Event event = findEventById(eventId);
        event.setEventTitle(updatedEvent.getEventTitle());
        event.setMeetName(updatedEvent.getMeetName());
        event.setCategory(updatedEvent.getCategory());
        event.setEventDate(updatedEvent.getEventDate());
        event.setEventTime(updatedEvent.getEventTime());
        event.setEventLocation(updatedEvent.getEventLocation());
        return eventRepository.save(event);
    }

    // Delete an event
    public void deleteEvent(Integer eventId) {
        eventRepository.deleteById(eventId);
    }

    // Get all events
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    // Get an event by ID
    public Event findEventById(Integer eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
    }
    public List<Event> findEventsRegisteredByAthlete(Integer athleteId) {
        List<Event> events = eventRepository.findEventsByRegisteredAthlete(athleteId);
        if (events.isEmpty()) {
            throw new EntityNotFoundException("No registered events found for athlete ID: " + athleteId);
        }
        return events;
    }

    public List<Event> findEventsApprovedForAthlete(Integer athleteId) {
        List<Event> events = eventRepository.findEventsByApprovedAthlete(athleteId);
        if (events.isEmpty()) {
            throw new EntityNotFoundException("No approved events found for athlete ID: " + athleteId);
        }
        return events;
    }

    // Register an athlete (checks if athlete exists and avoids duplicates)
    public Event registerAthlete(Integer eventId, Integer athleteId) {
        Event event = findEventById(eventId);

        // Check if athlete exists in the Athlete table
        if (!athleteExists(athleteId)) {
            throw new RuntimeException("Athlete with ID " + athleteId + " does not exist in the athlete table.");
        }

        // Check for duplicates: if athlete is already in any list (registered, accepted, or declined)
        if (isAthleteInAnyList(event, athleteId)) {
            throw new RuntimeException("Athlete already registered, accepted, or declined for this event.");
        }

        // Add the athlete to the registered list
        event.getRegisteredAthletes().add(athleteId);
        return eventRepository.save(event);
    }


    // Accept an athlete's registration (removes from registered list and adds to accepted list)
    public Event acceptAthlete(Integer eventId, Integer athleteId) {
        Event event = findEventById(eventId);

        if (event.getRegisteredAthletes().contains(athleteId)) {
           // event.getRegisteredAthletes().remove(athleteId);

            if (!event.getAcceptedAthletes().contains(athleteId)) {
                event.getAcceptedAthletes().add(athleteId);
            }
        } else {
            throw new RuntimeException("Athlete must be registered before being accepted.");
        }

        return eventRepository.save(event);
    }

    // Decline an athlete's registration (removes from registered list and adds to declined list)
    public Event declineAthlete(Integer eventId, Integer athleteId) {
        Event event = findEventById(eventId);

        if (event.getRegisteredAthletes().contains(athleteId)) {
            //event.getRegisteredAthletes().remove(athleteId);

            if (!event.getDeclinedAthletes().contains(athleteId)) {
                event.getDeclinedAthletes().add(athleteId);
            }
        } else {
            throw new RuntimeException("Athlete must be registered before being declined.");
        }

        return eventRepository.save(event);
    }

    // Check if an athlete exists in the athlete table
    private boolean athleteExists(Integer athleteId) {
        return athleteRepository.existsById(athleteId);
    }

    // Check if an athlete exists in any list (registered, accepted, or declined)
    private boolean isAthleteInAnyList(Event event, Integer athleteId) {
        return event.getRegisteredAthletes().contains(athleteId) ||
                event.getAcceptedAthletes().contains(athleteId) ||
                event.getDeclinedAthletes().contains(athleteId);
    }
}
