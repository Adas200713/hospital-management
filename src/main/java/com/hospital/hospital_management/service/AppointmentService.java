package com.hospital.hospital_management.service;

import com.hospital.hospital_management.dto.AppointmentRequest;
import com.hospital.hospital_management.dto.AppointmentResponse;
import com.hospital.hospital_management.entity.Appointment;
import com.hospital.hospital_management.entity.AppointmentStatus;
import com.hospital.hospital_management.exception.TimeSlotConflictException;
import com.hospital.hospital_management.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 🔥 CREATE with conflict detection
    public AppointmentResponse createAppointment(AppointmentRequest request) {

        List<Appointment> existingAppointments =
                appointmentRepository.findByDoctorNameAndDate(
                        request.getDoctorName(),
                        request.getDate()
                );

        for (Appointment existing : existingAppointments) {
            if (isOverlapping(
                    request.getStartTime(),
                    request.getEndTime(),
                    existing.getStartTime(),
                    existing.getEndTime()
            )) {
                System.out.println("Throwing CONFLICT EXCEPTION");
                throw new TimeSlotConflictException("Time slot already booked for this doctor");
            }
        }

        Appointment appointment = new Appointment();
        appointment.setPatientName(request.getPatientName());
        appointment.setDoctorName(request.getDoctorName());
        appointment.setDate(request.getDate());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setStatus(AppointmentStatus.CONFIRMED);

        Appointment saved = appointmentRepository.save(appointment);

        return mapToResponse(saved);
    }

    // 🔥 UPDATE with conflict detection
    public AppointmentResponse updateAppointment(Long id, AppointmentRequest request) {


        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        List<Appointment> existingAppointments =
                appointmentRepository.findByDoctorNameAndDate(
                        request.getDoctorName(),
                        request.getDate()
                );

        for (Appointment existing : existingAppointments) {

            if (existing.getId().equals(id)) continue;

            if (isOverlapping(
                    request.getStartTime(),
                    request.getEndTime(),
                    existing.getStartTime(),
                    existing.getEndTime()
            )) {
                throw new TimeSlotConflictException("Time slot already booked for this doctor");

            }
        }

        appointment.setPatientName(request.getPatientName());
        appointment.setDoctorName(request.getDoctorName());
        appointment.setDate(request.getDate());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());

        Appointment updated = appointmentRepository.save(appointment);

        return mapToResponse(updated);
    }

    // 🔥 CANCEL
    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    // 🔥 GET ALL
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 🔥 GET BY ID
    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        return mapToResponse(appointment);
    }

    // 🔥 OVERLAP LOGIC (core feature)
    private boolean isOverlapping(
            LocalTime newStart,
            LocalTime newEnd,
            LocalTime existingStart,
            LocalTime existingEnd
    ) {
        return newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart);
    }

    // 🔥 MAPPER
    private AppointmentResponse mapToResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getPatientName(),
                appointment.getDoctorName(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getStatus()
        );
    }
}