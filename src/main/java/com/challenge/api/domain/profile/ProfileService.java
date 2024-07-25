package com.challenge.api.domain.profile;

import com.challenge.api.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    public Profile findByUserName(String userName) throws EntityNotFoundException {
        var profile = profileRepository.findByUserName(userName);

        if(profile.isEmpty()){
            throw new EntityNotFoundException("No profile with name: " + userName + " exists.");
        }

        return profile.get();
    }

    public User findUserByUserName(String userName) throws EntityNotFoundException {
        var user = profileRepository.findUserByUserName(userName);

        if(user.isEmpty()){
            throw new EntityNotFoundException("No user with name: " + userName + " exists.");
        }

        return user.get();
    }

    public Profile findById(Long id){
        var profile = profileRepository.findById(id);

        if(profile.isEmpty()){
            throw new EntityNotFoundException("No profile with id: " + id + " exists.");
        }

        return profile.get();
    }
}
