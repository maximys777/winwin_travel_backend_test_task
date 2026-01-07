package com.maximys777.authapi.service;

import com.maximys777.authapi.entity.UserEntity;
import com.maximys777.authapi.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return mapToAuthUser(user);
    }

    public UserDetails loadUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User email not found"));

        return mapToAuthUser(user);
    }

    private AuthUser mapToAuthUser(UserEntity user) {
        return new AuthUser(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );
    }
}