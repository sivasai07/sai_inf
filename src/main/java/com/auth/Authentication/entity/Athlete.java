package com.auth.Authentication.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Table(name = "Athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer athleteld; // Athlete ID

    @ManyToOne
    @JoinColumn(name = "userld", referencedColumnName = "id", nullable = false)
    private User user; // User entity reference

    @Column(nullable = true, length = 50,unique = true)
    private String username = "enter_default_username"; // Default username

    @Column(nullable = true, length = 50)
    private String firstName = "First Name"; // Default first name

    @Column(nullable = true, length = 50)
    private String lastName = "Last Name"; // Default last name

    @Column(nullable = true)
    private LocalDate birthDate = LocalDate.of(2005, 6, 12); // Default birth date

    @Column(nullable = true, length = 50)
    private String gender = "Unspecified"; // Default gender

    @Column(nullable = true)
    private Float height = 0.0f; // Default height

    @Column(nullable = true)
    private Float weight = 0.0f; // Default weight

    @Column(nullable = false, length = 50)
    private String category = "Uncategorized"; // Default category

    private String photoUrl = "here store your photo url"; // Default photo URL

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Default created timestamp

    private LocalDateTime updatedAt = LocalDateTime.now(); // Default updated timestamp

    @ManyToOne
    @JoinColumn(name = "coachId", referencedColumnName = "coachId", nullable = true) // Allow nullable coachId
    private Coach coach; // Coach entity reference

    // Getters and Setters
    public Integer getAthleteld() {
        return athleteld;
    }
    public Integer getId() {
        return athleteld;
    }
    public void setAthleteld(Integer athleteld) {
        this.athleteld = athleteld;
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

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public int getCoachId() {
        if (coach != null) {
            return coach.getCoachId();
        }
        return 0;
    }
}
