package com.auth.Authentication.Services;

import com.auth.Authentication.Repository.AssistantNotifyRepository;
import com.auth.Authentication.entity.AssistantNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssistantNotifyService {

    @Autowired
    private AssistantNotifyRepository assistantNotifyRepository;

    // Create new notification
    public AssistantNotify createNotification(Integer athleteId, Integer coachId, String message) {
        AssistantNotify notification = new AssistantNotify();
        notification.setAthleteId(athleteId);
        notification.setCoachId(coachId);
        notification.setMessage(message);
        notification.setCoachReply("");  // Empty initially
        notification.setSeen(false);
        notification.setStatus("NOT_SEEN");
        return assistantNotifyRepository.save(notification);
    }

    // Get notifications by athlete ID
    public List<AssistantNotify> getByAthleteId(Integer athleteId) {
        return assistantNotifyRepository.findByAthleteId(athleteId);
    }

    // Get notifications by coach ID
    public List<AssistantNotify> getByCoachId(Integer coachId) {
        return assistantNotifyRepository.findByCoachId(coachId);
    }

    // Update coach reply and status
    public AssistantNotify updateNotification(Integer id, String coachReply, String status) {
        Optional<AssistantNotify> existingNotification = assistantNotifyRepository.findById(id);

        if (existingNotification.isPresent()) {
            AssistantNotify notification = existingNotification.get();
            notification.setCoachReply(coachReply);
            notification.setStatus(status);
            return assistantNotifyRepository.save(notification);
        }
        return null;
    }
}
