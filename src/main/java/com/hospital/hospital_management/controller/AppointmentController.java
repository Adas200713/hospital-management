
package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.dto.AppointmentRequest;
import com.hospital.hospital_management.dto.AppointmentResponse;
import com.hospital.hospital_management.service.AppointmentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    //  CREATE APPOINTMENT
    @PostMapping
    public AppointmentResponse createAppointment(
            @Valid @RequestBody AppointmentRequest request) {

        return appointmentService.createAppointment(request);
    }

    // GET ALL APPOINTMENTS
    @GetMapping
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // GET APPOINTMENT BY ID
    @GetMapping("/{id}")
    public AppointmentResponse getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    //  UPDATE APPOINTMENT
    @PutMapping("/{id}")
    public AppointmentResponse updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequest request) {

        return appointmentService.updateAppointment(id, request);
    }

    // CANCEL APPOINTMENT
    @DeleteMapping("/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "Appointment cancelled successfully";
    }
}