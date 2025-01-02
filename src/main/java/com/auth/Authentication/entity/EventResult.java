package com.auth.Authentication.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "event_result",
        uniqueConstraints = @UniqueConstraint(columnNames = {"athlete_id", "event_id"})
)
public class EventResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "athlete_id", nullable = false)
    private Integer athleteId;

    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @Column(nullable = true, length = 255) // Allowing null and defining max length for score and remarks
    private String score;   // Store the score as a String (could be numeric)

    @Column(nullable = true, length = 500) // Allowing null and setting max length for remarks
    private String remarks; // Store remarks as a String

    @Column(name = "published_date", nullable = false)
    private LocalDateTime publishedDate; // When the result was published

    // Automatically set the publishedDate to now when persisting
    @PrePersist
    public void prePersist() {
        if (this.publishedDate == null) {
            this.publishedDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.publishedDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Integer athleteId) {
        this.athleteId = athleteId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

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

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }
}
