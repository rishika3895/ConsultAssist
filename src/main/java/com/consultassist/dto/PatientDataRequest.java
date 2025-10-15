package com.consultassist.dto;

public class PatientDataRequest {
    private String name;
    private Integer age;
    private String symptoms;
    private String medicalHistory;
    
    // Constructors
    public PatientDataRequest() {}
    
    public PatientDataRequest(String name, Integer age, String symptoms, String medicalHistory) {
        this.name = name;
        this.age = age;
        this.symptoms = symptoms;
        this.medicalHistory = medicalHistory;
    }
    
    // Getters and Setters
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
}