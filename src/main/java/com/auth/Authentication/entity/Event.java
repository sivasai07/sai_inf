package com.auth.Authentication.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    private String eventTitle;
    private String meetName;
    private String category;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String eventLocation;
    private String photoUrl; // Added photoUrl field

    @ElementCollection
    private List<Integer> registeredAthletes = new ArrayList<>();

    @ElementCollection
    private List<Integer> acceptedAthletes = new ArrayList<>();

    @ElementCollection
    private List<Integer> declinedAthletes = new ArrayList<>();

    // Getters and Setters

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Integer> getRegisteredAthletes() {
        return registeredAthletes;
    }

    public void setRegisteredAthletes(List<Integer> registeredAthletes) {
        this.registeredAthletes = registeredAthletes;
    }

    public List<Integer> getAcceptedAthletes() {
        return acceptedAthletes;
    }

    public void setAcceptedAthletes(List<Integer> acceptedAthletes) {
        this.acceptedAthletes = acceptedAthletes;
    }

    public List<Integer> getDeclinedAthletes() {
        return declinedAthletes;
    }

    public void setDeclinedAthletes(List<Integer> declinedAthletes) {
        this.declinedAthletes = declinedAthletes;
    }
}