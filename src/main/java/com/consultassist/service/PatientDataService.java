package com.consultassist.service;

import com.consultassist.entity.PatientData;
import com.consultassist.entity.User;
import com.consultassist.repository.PatientDataRepository;
import com.consultassist.dto.PatientDataRequest;
import com.consultassist.dto.PatientDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientDataService {
    
    @Autowired
    private PatientDataRepository patientDataRepository;
    
    public PatientData savePatientData(PatientDataRequest request, User patient) {
        PatientData patientData = new PatientData(
            request.getName(),
            request.getAge(),
            request.getSymptoms(),
            request.getMedicalHistory(),
            patient
        );
        return patientDataRepository.save(patientData);
    }
    
    public List<PatientDataResponse> getAllPatientData() {
        return patientDataRepository.findAllByOrderBySubmissionDateDesc()
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<PatientDataResponse> getPatientDataByUser(User patient) {
        return patientDataRepository.findByPatient(patient)
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    private PatientDataResponse convertToResponse(PatientData patientData) {
        return new PatientDataResponse(
            patientData.getId(),
            patientData.getName(),
            patientData.getAge(),
            patientData.getSymptoms(),
            patientData.getMedicalHistory(),
            patientData.getSubmissionDate(),
            patientData.getPatient().getUsername()
        );
    }
}