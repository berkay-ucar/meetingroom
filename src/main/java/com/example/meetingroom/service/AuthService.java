package com.example.meetingroom.service;

import com.example.meetingroom.dto.LoginRequest;
import com.example.meetingroom.entity.User;
import com.example.meetingroom.repository.UserRepository;
import com.example.meetingroom.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(LoginRequest request){

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        Map<String,Object> claims = new HashMap<>();
        claims.put("role", user.getRoles());
        claims.put("userId", user.getId());

        return jwtService.generateToken(claims, user.getEmail());
    }

}