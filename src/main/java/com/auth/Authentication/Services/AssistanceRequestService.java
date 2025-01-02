package com.auth.Authentication.Services;

import com.auth.Authentication.Repository.AssistanceRequestRepository;
import com.auth.Authentication.Repository.AthleteRepository;
import com.auth.Authentication.Repository.CoachRepository;
import com.auth.Authentication.entity.AssistanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class AssistanceRequestService {

    @Autowired
    private AssistanceRequestRepository assistanceRequestRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private NotificationService notificationService;

    // Create a new Assistance Request
    public AssistanceRequest createRequest(int athleteId, int coachId) {
        if (!athleteRepository.existsById(athleteId)) {
            throw new IllegalArgumentException("Athlete with ID " + athleteId + " not found.");
        }
        if (!coachRepository.existsById(coachId)) {
            throw new IllegalArgumentException("Coach with ID " + coachId + " not found.");
        }

        if (assistanceRequestRepository.findByAthleteIdAndCoachId(athleteId, coachId).isPresent()) {
            throw new IllegalArgumentException("Request already exists between this athlete and coach.");
        }

        AssistanceRequest request = new AssistanceRequest();
        request.setAthleteId(athleteId);
        request.setCoachId(coachId);
        request.setStatus("PENDING");
        request.setTimestamp(LocalDateTime.now());

        return assistanceRequestRepository.save(request);
    }

    // Get all requests for a specific coach
    public List<AssistanceRequest> getRequestsForCoach(int coachId) {
        return assistanceRequestRepository.findByCoachId(coachId);
    }

    // Get all requests for a specific athlete
    public List<AssistanceRequest> getRequestsForAthlete(int athleteId) {
        return assistanceRequestRepository.findByAthleteId(athleteId);
    }

    // Accept a request with remarks
    public AssistanceRequest acceptRequest(int athleteId, int coachId, String remarks) {
        AssistanceRequest request = assistanceRequestRepository.findByAthleteIdAndCoachId(athleteId, coachId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        request.setStatus("ACCEPTED");
        request.setRemarks(remarks);
        assistanceRequestRepository.save(request);

        notificationService.sendNotification(athleteId, "Your request to coach has been accepted!");

        return request;
    }

    // Reject a request with remarks
    public AssistanceRequest rejectRequest(int athleteId, int coachId, String remarks) {
        AssistanceRequest request = assistanceRequestRepository.findByAthleteIdAndCoachId(athleteId, coachId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        request.setStatus("REJECTED");
        request.setRemarks(remarks);
        return assistanceRequestRepository.save(request);
    }
    // Retrieve the status of a specific assistance request
    public String getRequestStatus(int athleteId, int coachId) {
        AssistanceRequest request = assistanceRequestRepository.findByAthleteIdAndCoachId(athleteId, coachId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        return request.getStatus();
    }
    // Check if a specific athlete-coach combination exists
    public boolean checkIfRequestExists(int athleteId, int coachId) {
        return assistanceRequestRepository.findByAthleteIdAndCoachId(athleteId, coachId).isPresent();
    }

}
