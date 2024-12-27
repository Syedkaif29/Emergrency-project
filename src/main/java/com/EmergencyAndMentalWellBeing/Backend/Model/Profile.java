package com.EmergencyAndMentalWellBeing.Backend.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @ElementCollection
    private List<String> appointments;

    @ElementCollection
    private List<AssessmentScore> assessmentScores;

    // Getters and Setters
}

