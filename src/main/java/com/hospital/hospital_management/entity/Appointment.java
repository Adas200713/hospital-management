
package com.hospital.hospital_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Patient Name
    @NotBlank(message = "Patient name cannot be empty")
    @Column(nullable = false)
    private String patientName;

    // Doctor Name (you can later replace with Doctor entity relation)
    @NotBlank(message = "Doctor name cannot be empty")
    @Column(nullable = false)
    private String doctorName;

    // Appointment Date
    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;

    // Start Time
    @NotNull(message = "Start time is required")
    @Column(nullable = false)
    private LocalTime startTime;

    // End Time
    @NotNull(message = "End time is required")
    @Column(nullable = false)
    private LocalTime endTime;

    // Optional: Status (good for interview discussion)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    // 🔽 Constructors

    public Appointment() {
    }

    public Appointment(String patientName, String doctorName,
                       LocalDate date, LocalTime startTime, LocalTime endTime,
                       AppointmentStatus status) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // 🔽 Getters & Setters

    public Long getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}