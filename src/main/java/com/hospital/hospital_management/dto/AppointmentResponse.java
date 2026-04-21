package com.hospital.hospital_management.dto;

import com.hospital.hospital_management.entity.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentResponse {

    private Long id;
    private String patientName;
    private String doctorName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private AppointmentStatus status;

    // Constructor (IMPORTANT — must match mapper)
    public AppointmentResponse(
            Long id,
            String patientName,
            String doctorName,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            AppointmentStatus status
    ) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }
}