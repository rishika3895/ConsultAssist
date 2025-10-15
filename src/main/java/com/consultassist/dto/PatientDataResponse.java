package com.consultassist.dto;

import java.time.LocalDateTime;

public class PatientDataResponse {
    private Long id;
    private String name;
    private Integer age;
    private String symptoms;
    private String medicalHistory;
    private LocalDateTime submissionDate;
    private String patientUsername;
    
    // Constructors
    public PatientDataResponse() {}
    
    public PatientDataResponse(Long id, String name, Integer age, String symptoms, 
                              String medicalHistory, LocalDateTime submissionDate, String patientUsername) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
        this.medicalHistory = medicalHistory;
        this.submissionDate = submissionDate;
        this.patientUsername = patientUsername;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getSymptoms() {
        return symptoms;
    }
    
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
    
    public String getMedicalHistory() {
        return medicalHistory;
    }
    
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }
    
    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }
    
    public String getPatientUsername() {
        return patientUsername;
    }
    
    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }
}