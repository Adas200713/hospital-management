package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.entity.Patient;
import com.hospital.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Add patient
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    // Get all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "Patient deleted succesfully";
    }
}
