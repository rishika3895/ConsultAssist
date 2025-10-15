package com.consultassist.controller;

import com.consultassist.dto.PatientDataRequest;
import com.consultassist.dto.PatientDataResponse;
import com.consultassist.entity.User;
import com.consultassist.service.PatientDataService;
import com.consultassist.service.UserService;
import com.consultassist.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientDataController {
    
    @Autowired
    private PatientDataService patientDataService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/patient/data")
    public ResponseEntity<?> submitPatientData(
            @RequestBody PatientDataRequest patientDataRequest,
            @RequestHeader("Authorization") String token) {
        try {
            // Extract user info from the token
            String username = jwtUtil.getUsernameFromToken(token.substring(7)); // Remove "Bearer " prefix
            Optional<User> userOptional = userService.findByUsername(username);
            
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                // Save the patient data
                patientDataService.savePatientData(patientDataRequest, user);
                return ResponseEntity.ok("Patient data submitted successfully");
            } else {
                return ResponseEntity.status(401).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error submitting patient data: " + e.getMessage());
        }
    }
    
    @GetMapping("/doctor/patients")
    public ResponseEntity<List<PatientDataResponse>> getAllPatientData() {
        try {
            List<PatientDataResponse> patientDataList = patientDataService.getAllPatientData();
            return ResponseEntity.ok(patientDataList);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}