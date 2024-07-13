package com.challenge.api.infra.security;

import com.challenge.api.domain.profile.ProfileRepository;
import com.challenge.api.domain.user.User;
import com.challenge.api.domain.user.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO login(LoginAuthDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.userName(), request.password()));
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Incorrect user name or password");
        }
        var userInDB = profileRepository.findUserByUserName(request.userName());

        if(userInDB.isEmpty()){
            throw new RuntimeException("The user name not exists.");
        }

        UserDetails user = userInDB.get();

        final String token = jwtService.getToken(user);

        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO register(RegisterAuthDTO request) {
        User user = new User(request, passwordEncoder);

        if(profileRepository.findByUserName(request.userName()).isPresent()){
            throw new ValidationException("The user name, already exist.");
        }

        profileRepository.save(user.getProfile());
        userRepository.save(user);

        return new AuthResponseDTO(jwtService.getToken(user));
    }
}
