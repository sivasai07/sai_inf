package com.auth.Authentication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assistant_notify")
public class AssistantNotify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer athleteId;
    private Integer coachId;
    private String message;
    private String coachReply;
    private Boolean seen;
    private String status;

    // Default constructor
    public AssistantNotify() {
    }

    // Parameterized constructor
    public AssistantNotify(Integer id, Integer athleteId, Integer coachId, String message,
                           String coachReply, Boolean seen, String status) {
        this.id = id;
        this.athleteId = athleteId;
        this.coachId = coachId;
        this.message = message;
        this.coachReply = coachReply;
        this.seen = seen;
        this.status = status;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Integer athleteId) {
        this.athleteId = athleteId;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCoachReply() {
        return coachReply;
    }

    public void setCoachReply(String coachReply) {
        this.coachReply = coachReply;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}