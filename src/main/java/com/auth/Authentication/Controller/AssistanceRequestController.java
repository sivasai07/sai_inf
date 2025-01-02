package com.auth.Authentication.Controller;

import com.auth.Authentication.Services.AssistanceRequestService;
import com.auth.Authentication.entity.AssistanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/assistance-requests")
public class AssistanceRequestController {

    @Autowired
    private AssistanceRequestService assistanceRequestService;

    // Create a new assistance request
    @PostMapping("/create")
    public AssistanceRequest createAssistanceRequest(@RequestParam int athleteId, @RequestParam int coachId) {
        return assistanceRequestService.createRequest(athleteId, coachId);
    }

    // Get all requests for a coach
    @GetMapping("/coach/{coachId}")
    public List<AssistanceRequest> getRequestsForCoach(@PathVariable int coachId) {
        return assistanceRequestService.getRequestsForCoach(coachId);
    }

    // Get all requests for an athlete
    @GetMapping("/athlete/{athleteId}")
    public List<AssistanceRequest> getRequestsForAthlete(@PathVariable int athleteId) {
        return assistanceRequestService.getRequestsForAthlete(athleteId);
    }

    // Accept an assistance request with remarks
    @PutMapping("/accept")
    public AssistanceRequest acceptRequest(@RequestParam int athleteId, @RequestParam int coachId, @RequestParam String remarks) {
        return assistanceRequestService.acceptRequest(athleteId, coachId, remarks);
    }

    // Reject an assistance request with remarks
    @PutMapping("/reject")
    public AssistanceRequest rejectRequest(@RequestParam int athleteId, @RequestParam int coachId, @RequestParam String remarks) {
        return assistanceRequestService.rejectRequest(athleteId, coachId, remarks);
    }
    // Check the status of a specific assistance request
    @GetMapping("/status")
    public String getRequestStatus(@RequestParam int athleteId, @RequestParam int coachId) {
        return assistanceRequestService.getRequestStatus(athleteId, coachId);
    }
    // Check if a specific athlete-coach combination exists
    @GetMapping("/exists")
    public boolean checkIfRequestExists(@RequestParam int athleteId, @RequestParam int coachId) {
        return assistanceRequestService.checkIfRequestExists(athleteId, coachId);
    }


}
