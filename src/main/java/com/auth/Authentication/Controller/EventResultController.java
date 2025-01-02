package com.auth.Authentication.Controller;

import com.auth.Authentication.Services.ResultService;
import com.auth.Authentication.dto.ResultDto;
import com.auth.Authentication.entity.EventResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event-results")
public class EventResultController {

    private final ResultService resultService;

    public EventResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishResults(@RequestBody List<ResultDto> results) {
        try {
            resultService.publishResults(results);
            return ResponseEntity.ok("Results published successfully!");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error publishing results: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<EventResult>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<?> getResults(@PathVariable String eventId) {
        if ("all".equalsIgnoreCase(eventId)) {
            // Fetch all results when "all" is passed
            return ResponseEntity.ok(resultService.getAllResults());
        } else {
            try {
                Integer eventIdInt = Integer.parseInt(eventId);
                return ResponseEntity.ok(resultService.getResultsByEventId(eventIdInt));
            } catch (NumberFormatException ex) {
                return ResponseEntity.badRequest().body("Invalid eventId: " + eventId);
            }
        }
    }


    @GetMapping("/{eventId}/{athleteId}")
    public ResponseEntity<?> getResultByAthleteIdAndEventId(@PathVariable Integer athleteId, @PathVariable Integer eventId) {
        Optional<EventResult> result = resultService.getResultByAthleteIdAndEventId(athleteId, eventId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete all results
    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAllResults() {
        try {
            resultService.deleteAllResults();
            return ResponseEntity.ok("All results deleted successfully!");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error deleting results: " + ex.getMessage());
        }
    }

    // Update the score and remarks for a specific result
    @PatchMapping("/update-score/{eventId}/{athleteId}")
    public ResponseEntity<?> updateScore(@PathVariable Integer eventId, @PathVariable Integer athleteId, @RequestBody UpdateScoreRequest request) {
        try {
            boolean isUpdated = resultService.updateScore(athleteId, eventId, request.getScore(), request.getRemarks());
            if (isUpdated) {
                return ResponseEntity.ok("Score and remarks updated successfully!");
            } else {
                return ResponseEntity.status(404).body("Result not found for the given athleteId and eventId.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error updating score: " + ex.getMessage());
        }
    }

    // DTO for receiving score and remarks
    public static class UpdateScoreRequest {
        private String score;
        private String remarks;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
