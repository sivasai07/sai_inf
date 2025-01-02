package com.auth.Authentication.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AssistanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "athlete_id", nullable = false)
    private int athleteId;

    @Column(name = "coach_id", nullable = false)
    private int coachId;

    @Column(nullable = false)
    private String status = "PENDING"; // Default to PENDING

    private LocalDateTime timestamp;

    @Column(length = 500) // Specify the maximum length for remarks if needed
    private String remarks;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(int athleteId) {
        this.athleteId = athleteId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
