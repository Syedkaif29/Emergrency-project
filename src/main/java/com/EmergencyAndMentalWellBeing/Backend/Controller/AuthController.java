package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import com.EmergencyAndMentalWellBeing.Backend.Model.Consultant;
import com.EmergencyAndMentalWellBeing.Backend.Repository.UserRepo;
import com.EmergencyAndMentalWellBeing.Backend.Repository.ConsultantRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ConsultantRepo consultantRepository;

    // User Registration API
    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@org.jetbrains.annotations.NotNull @RequestBody Users user) {
        if (user.getFirstname() == null || user.getFirstname().isEmpty() ||
                user.getLastname() == null || user.getLastname().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPhonenumber() == null || user.getPhonenumber().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }

        try {
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while saving user: " + e.getMessage());
        }
    }

    // Consultant Registration API
    @PostMapping("/register/consultant")
    public ResponseEntity<?> registerConsultant(@RequestBody @NotNull Consultant consultant) {
        if (consultant.getFirstname() == null || consultant.getFirstname().isEmpty() ||
                consultant.getLastname() == null || consultant.getLastname().isEmpty() ||
                consultant.getEmail() == null || consultant.getEmail().isEmpty() ||
                consultant.getPhonenumber() == null || consultant.getPhonenumber().isEmpty() ||
                consultant.getPassword() == null || consultant.getPassword().isEmpty() ||
                consultant.getConfirmPassword() == null || consultant.getConfirmPassword().isEmpty() ||
                consultant.getSpecialization() == null || consultant.getSpecialization().isEmpty() ||
                consultant.getLicenseNumber() == null || consultant.getLicenseNumber().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required.");
        }

        if (consultantRepository.findByEmail(consultant.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        if (!consultant.getPassword().equals(consultant.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }

        try {
            consultantRepository.save(consultant);
            return ResponseEntity.ok("Consultant registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while saving consultant: " + e.getMessage());
        }
    }

    // Login API for User
    @PostMapping("/login/user")
    public ResponseEntity<?> loginUser(@RequestBody @NotNull Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        Users user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "User login successful.");
        return ResponseEntity.ok(response);
    }

    // Login API for Consultant
    @PostMapping("/login/consultant")
    public ResponseEntity<?> loginConsultant(@RequestBody @NotNull Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        Consultant consultant = consultantRepository.findByEmail(email);
        if (consultant == null || !consultant.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Invalid email or password.");
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Consultant login successful.");
        response.put("specialization", consultant.getSpecialization());
        response.put("licenseNumber", consultant.getLicenseNumber());
        return ResponseEntity.ok(response);
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // Get user by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found.");
        }
        return ResponseEntity.ok(user);
    }

    // Update user details
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody Users updatedUser) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found.");
        }

        user.setFirstname(updatedUser.getFirstname());
        user.setLastname(updatedUser.getLastname());
        user.setEmail(updatedUser.getEmail());
        user.setPhonenumber(updatedUser.getPhonenumber());
        user.setPassword(updatedUser.getPassword());

        try {
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while updating user: " + e.getMessage());
        }
    }

    // Delete user by userId
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found.");
        }

        try {
            userRepository.delete(user);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while deleting user: " + e.getMessage());
        }
    }
}
