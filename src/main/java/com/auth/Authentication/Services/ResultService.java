package com.auth.Authentication.Services;

import com.auth.Authentication.Repository.EventResultRepository;
import com.auth.Authentication.dto.ResultDto;
import com.auth.Authentication.entity.EventResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResultService {

    private final EventResultRepository eventResultRepository;

    public ResultService(EventResultRepository eventResultRepository) {
        this.eventResultRepository = eventResultRepository;
    }

    // Publish the results (insert or update)
    public void publishResults(List<ResultDto> results) {
        for (ResultDto dto : results) {
            Optional<EventResult> existingResult = eventResultRepository.findByAthleteIdAndEventId(dto.getAthleteId(), dto.getEventId());

            if (existingResult.isPresent()) {
                // If result exists, update score and remarks
                EventResult result = existingResult.get();
                result.setScore(dto.getScore());  // Set score
                result.setRemarks(dto.getRemarks());  // Set remarks
                result.setPublishedDate(LocalDateTime.now());  // Set the current date/time
                eventResultRepository.save(result);  // Save updated result
            } else {
                // If result does not exist, create a new entry
                EventResult result = new EventResult();
                result.setAthleteId(dto.getAthleteId());
                result.setEventId(dto.getEventId());
                result.setScore(dto.getScore());
                result.setRemarks(dto.getRemarks());
                result.setPublishedDate(LocalDateTime.now());  // Set the current date/time
                eventResultRepository.save(result);  // Save the new result
            }
        }
    }

    // Get all results (make sure this method is implemented)
    public List<EventResult> getAllResults() {
        return eventResultRepository.findAll();  // This will return all event results from the repository
    }

    // Get all results for a specific event
    public List<EventResult> getResultsByEventId(Integer eventId) {
        return eventResultRepository.findByEventId(eventId);
    }

    // Get a specific result by athleteId and eventId
    public Optional<EventResult> getResultByAthleteIdAndEventId(Integer athleteId, Integer eventId) {
        return eventResultRepository.findByAthleteIdAndEventId(athleteId, eventId);
    }

    // Update score and remarks for an existing result
    public boolean updateScore(Integer athleteId, Integer eventId, String newScore, String remarks) {
        Optional<EventResult> existingResult = eventResultRepository.findByAthleteIdAndEventId(athleteId, eventId);

        if (existingResult.isPresent()) {
            EventResult result = existingResult.get();
            result.setScore(newScore);  // Update score
            result.setRemarks(remarks);  // Update remarks
            result.setPublishedDate(LocalDateTime.now()); // Update the published date
            eventResultRepository.save(result);  // Save the updated result
            return true;
        } else {
            return false; // No result found to update
        }
    }


    // Delete all results (if needed)
    public void deleteAllResults() {
        eventResultRepository.deleteAll();
    }
}
