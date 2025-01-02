package com.auth.Authentication.Controller;

import com.auth.Authentication.Services.AssistantNotifyService;
import com.auth.Authentication.entity.AssistantNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coach_notifications")
public class AssistantNotifyController {

    @Autowired
    private AssistantNotifyService assistantNotifyService;

    @PostMapping("/create")
    public ResponseEntity<AssistantNotify> createNotification(
            @RequestParam Integer athleteId,
            @RequestParam Integer coachId,
            @RequestParam String message) {
        AssistantNotify notification = assistantNotifyService.createNotification(athleteId, coachId, message);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/athlete/{athleteId}")
    public ResponseEntity<List<AssistantNotify>> getByAthleteId(@PathVariable Integer athleteId) {
        List<AssistantNotify> notifications = assistantNotifyService.getByAthleteId(athleteId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/coach/{coachId}")
    public ResponseEntity<List<AssistantNotify>> getByCoachId(@PathVariable Integer coachId) {
        List<AssistantNotify> notifications = assistantNotifyService.getByCoachId(coachId);
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssistantNotify> updateNotification(
            @PathVariable Integer id,
            @RequestParam String coachReply,
            @RequestParam String status) {
        AssistantNotify notification = assistantNotifyService.updateNotification(id, coachReply, status);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.notFound().build();
    }
}

