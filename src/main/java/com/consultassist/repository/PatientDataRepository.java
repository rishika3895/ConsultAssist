package com.consultassist.repository;

import com.consultassist.entity.PatientData;
import com.consultassist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientDataRepository extends JpaRepository<PatientData, Long> {
    List<PatientData> findByPatient(User patient);
    List<PatientData> findAllByOrderBySubmissionDateDesc();
}