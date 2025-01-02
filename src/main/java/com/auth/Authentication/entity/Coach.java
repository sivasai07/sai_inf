package com.auth.Authentication.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Coaches")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coachId; // Primary Key

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user; // User entity reference (foreign key)

    @Column(length = 50, nullable = false, unique = true)
    private String username = "default_coach_username"; // Default username

    @Column(length = 50, nullable = false)
    private String firstName = "Default Firstname"; // Default first name

    @Column(length = 50, nullable = false)
    private String lastName = "Default Lastname"; // Default last name

    @Column(nullable = false)
    private LocalDate birthDate = LocalDate.of(2000, 1, 1); // Default birth date

    @Column(length = 50, nullable = false)
    private String gender = "Unspecified"; // Default gender

    @Column(length = 50, nullable = false)
    private String category = "Null"; // Default category

    @Column(length = 255)
    private String photoUrl = "http://default-photo-url.com"; // Default photo URL

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt; // Created timestamp

    @Column(nullable = false)
    private LocalDate updatedAt; // Updated timestamp

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Athlete> athletes; // List of athletes associated with the coach

    // Automatically set timestamps
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

    // Getters and Setters
    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }
}