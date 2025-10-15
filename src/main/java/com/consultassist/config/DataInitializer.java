package com.consultassist.config;

import com.consultassist.entity.User;
import com.consultassist.entity.Role;
import com.consultassist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create sample users if they don't exist
        if (userRepository.findByUsername("patient1").isEmpty()) {
            User patient = new User("patient1", passwordEncoder.encode("password123"), Role.PATIENT);
            userRepository.save(patient);
        }
        
        if (userRepository.findByUsername("doctor1").isEmpty()) {
            User doctor = new User("doctor1", passwordEncoder.encode("password123"), Role.DOCTOR);
            userRepository.save(doctor);
        }
    }
}