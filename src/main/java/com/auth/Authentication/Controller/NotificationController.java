package com.auth.Authentication.Controller;

import com.auth.Authentication.Repository.NotificationRepository;
import com.auth.Authentication.Services.NotificationService;
import com.auth.Authentication.dto.NotificationRequest;
import com.auth.Authentication.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest request) {
        try {
            notificationService.sendNotification(request.getAthleteId(), request.getMessage());
            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification");
        }
    }

    @GetMapping("/athlete/{athleteId}")
    public ResponseEntity<?> getNotificationsForAthlete(@PathVariable Integer athleteId) {
        try {
            List<Notification> notifications = notificationService.getNotificationsByAthleteId(athleteId);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve notifications");
        }
    }
    }

