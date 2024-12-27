package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Profile;
import com.EmergencyAndMentalWellBeing.Backend.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @PostMapping
    public Profile createOrUpdateProfile(@RequestBody Profile profile) {
        return profileService.createOrUpdateProfile(profile);
    }
}
