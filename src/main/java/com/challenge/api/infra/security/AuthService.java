package com.challenge.api.infra.security;

import com.challenge.api.model.profile.ProfileRepository;
import com.challenge.api.model.user.User;
import com.challenge.api.model.user.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    private final TokenJWTService tokenService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthRequestDTO login(LoginAuthDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.userName(), request.password()));

        UserDetails user =  profileRepository.findUserByUserName(request.userName()).orElseThrow();

        String token = tokenService.getToken(user);

        return new AuthRequestDTO(token);
    }

    public AuthRequestDTO register(RegisterAuthDTO request) {
        User user = new User(request, passwordEncoder);

        if(profileRepository.findByUserName(request.userName()).isPresent()){
            throw new ValidationException("The user name, already exist.");
        }

        profileRepository.save(user.getProfile());
        userRepository.save(user);

        return new AuthRequestDTO(tokenService.getToken(user));
    }
}
