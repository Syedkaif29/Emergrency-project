package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.Profile;
import com.EmergencyAndMentalWellBeing.Backend.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile createOrUpdateProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
