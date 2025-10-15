package com.consultassist.controller;

import com.consultassist.dto.LoginRequest;
import com.consultassist.dto.LoginResponse;
import com.consultassist.entity.User;
import com.consultassist.entity.Role;
import com.consultassist.service.UserService;
import com.consultassist.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOptional = userService.findByUsernameAndRole(
                loginRequest.getUsername(), 
                Role.valueOf(loginRequest.getRole().toUpperCase())
            );
            
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                    String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name(), user.getId());
                    return ResponseEntity.ok(new LoginResponse(token, user.getRole().name(), user.getId()));
                }
            }
            
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during authentication");
        }
    }
    
    // For demo purposes, we'll have a simple register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest registerRequest) {
        try {
            // Check if user already exists
            if (userService.findByUsername(registerRequest.getUsername()).isPresent()) {
                return ResponseEntity.status(400).body("Username already exists");
            }
            
            Role role = Role.valueOf(registerRequest.getRole().toUpperCase());
            User newUser = userService.createUser(
                registerRequest.getUsername(), 
                registerRequest.getPassword(), 
                role
            );
            
            String token = jwtUtil.generateToken(newUser.getUsername(), newUser.getRole().name(), newUser.getId());
            return ResponseEntity.ok(new LoginResponse(token, newUser.getRole().name(), newUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during registration");
        }
    }
}