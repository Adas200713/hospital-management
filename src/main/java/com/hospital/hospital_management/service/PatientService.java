package com.hospital.hospital_management.service;

import com.hospital.hospital_management.entity.Patient;
import com.hospital.hospital_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Add patient
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
    public void deletePatient(Long id){
        Patient patient=patientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient not found"));
        patientRepository.delete(patient);
    }
}
