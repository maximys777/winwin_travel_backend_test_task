package com.maximys777.authapi.service;

import com.maximys777.authapi.dto.request.AuthRequest;
import com.maximys777.authapi.dto.response.AuthResponse;
import com.maximys777.authapi.entity.UserEntity;
import com.maximys777.authapi.exception.exceptions.AlreadyExistsException;
import com.maximys777.authapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(AuthRequest authRequest) {
        validateUserAlreadyExists(authRequest.email());

        UserEntity user = UserEntity.builder()
                .email(authRequest.email())
                .password(passwordEncoder.encode(authRequest.password()))
                .build();

        userRepository.save(user);

        AuthUser authUser = new AuthUser(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );

        String token = jwtService.generateToken(authUser);

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()
                ));

        UserEntity user = userRepository.findByEmail(authRequest.email()).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));

        AuthUser authUser = new AuthUser(user.getId(), user.getEmail(), user.getPassword());
        String token = jwtService.generateToken(authUser);

        return new AuthResponse(token);
    }

    private void validateUserAlreadyExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new AlreadyExistsException("Email Already Exists");
        }
    }
}
