package com.sanjeet.expensetracker.service;

import com.sanjeet.expensetracker.dto.*;
import com.sanjeet.expensetracker.model.User;
import com.sanjeet.expensetracker.repository.UserRepository;
import com.sanjeet.expensetracker.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public Map<String, Object> signup(SignupRequest req) {
        if (userRepo.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email already registered");
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .build();
        userRepo.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return Map.of("token", token, "name", user.getName(), "email", user.getEmail());
    }

    public Map<String, Object> login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");
        String token = jwtUtil.generateToken(user.getEmail());
        return Map.of("token", token, "name", user.getName(), "email", user.getEmail());
    }
}