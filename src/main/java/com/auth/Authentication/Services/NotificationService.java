package com.auth.Authentication.Services;

import com.auth.Authentication.Repository.NotificationRepository;
import com.auth.Authentication.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(Integer athleteId, String message) {
        Notification notification = new Notification();
        notification.setAthleteId(athleteId);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByAthleteId(Integer athleteId) {
        return notificationRepository.findByAthleteId(athleteId);
    }
}


