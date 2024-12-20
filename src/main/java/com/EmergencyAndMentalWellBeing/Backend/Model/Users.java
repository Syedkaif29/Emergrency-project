package com.EmergencyAndMentalWellBeing.Backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Users")
public class Users {

    @Id
    private String userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phonenumber;
    private String password;
    private String confirmPassword;

    // Field to store self-assessment scores
    private List<AssessmentScore> assessmentScores = new ArrayList<>();

    // Inner class to represent individual assessment scores
    public static class AssessmentScore {
        private int totalScore;
        private String feedback;
        private LocalDateTime timestamp;

        // Constructors


        public AssessmentScore(int totalScore, String feedback, LocalDateTime timestamp) {
            this.totalScore = totalScore;
            this.feedback = feedback;
            this.timestamp = timestamp;
        }

        // Getters and setters
        public int getTotalScore() { return totalScore; }
        public void setTotalScore(int totalScore) { this.totalScore = totalScore; }

        public String getFeedback() { return feedback; }
        public void setFeedback(String feedback) { this.feedback = feedback; }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    }

    // Constructors
    public Users() {}

    public Users(String firstname, String lastname, String username, String email, String phonenumber, String password, String confirmPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // Getters and setters for user fields
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    // Getters and setters for assessment scores
    public List<AssessmentScore> getAssessmentScores() { return assessmentScores; }
    public void setAssessmentScores(List<AssessmentScore> assessmentScores) { this.assessmentScores = assessmentScores; }
}
