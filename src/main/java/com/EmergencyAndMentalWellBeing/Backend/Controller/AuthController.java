package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Users;
import com.EmergencyAndMentalWellBeing.Backend.Model.Consultant;
import com.EmergencyAndMentalWellBeing.Backend.Service.UserService;
import com.EmergencyAndMentalWellBeing.Backend.Service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConsultantService consultantService;

    // User Registration API
    @PostMapping("/register/user")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Users user) {
        String message = userService.registerUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("success", message.equals("User registered successfully."));
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    // Consultant Registration API
    @PostMapping("/register/consultant")
    public ResponseEntity<Map<String, Object>> registerConsultant(@RequestBody Consultant consultant) {
        String message = consultantService.registerConsultant(consultant);
        Map<String, Object> response = new HashMap<>();
        response.put("success", message.equals("Consultant registered successfully."));
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    // Login API for User
    @PostMapping("/login/user")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        String message = userService.loginUser(email, password);
        Map<String, Object> response = new HashMap<>();
        response.put("success", message.equals("User login successful."));
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    // Login API for Consultant
    @PostMapping("/login/consultant")
    public ResponseEntity<Map<String, Object>> loginConsultant(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        String message = consultantService.loginConsultant(email, password);
        Map<String, Object> response = new HashMap<>();
        response.put("success", message.equals("Consultant login successful."));
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    // Fetch User Profile by Email
    @GetMapping("/profile/user/{email}")
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable String email) {
        Users user = userService.getUserByEmail(email); // Assumes userService has a method to fetch user by email
        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            response.put("success", true);
            response.put("data", user);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "User not found.");
            return ResponseEntity.status(404).body(response);
        }
    }


    // Update User details
    @PutMapping("/update/user/{email}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String email, @RequestBody Users updatedUser) {
        String message = userService.updateUser(email, updatedUser);
        Map<String, Object> response = new HashMap<>();
        response.put("success", message.equals("User details updated successfully."));
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    // Update Consultant details
    @PutMapping("/update/consultant/{email}")
    public ResponseEntity<Map<String, Object>> updateConsultant(@PathVariable String email, @RequestBody Consultant updatedConsultant) {
        String message = consultantService.updateConsultant(email, updatedConsultant);
        Map<String, Object> response = new HashMap<>();
        response.put("success", message.equals("Consultant details updated successfully."));
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
}

