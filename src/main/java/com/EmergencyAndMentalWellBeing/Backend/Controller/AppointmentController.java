package com.EmergencyAndMentalWellBeing.Backend.Controller;

import com.EmergencyAndMentalWellBeing.Backend.Model.Appointment;
import com.EmergencyAndMentalWellBeing.Backend.Model.AppointmentStatus;
import com.EmergencyAndMentalWellBeing.Backend.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Create a new appointment request
    @PostMapping
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
        appointment.setStatus(AppointmentStatus.PENDING); // Set the initial status to PENDING
        Appointment savedAppointment = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    // Get all pending appointments for a consultant (by email)
    @GetMapping("/consultant/{consultantEmail}/pending")
    public ResponseEntity<List<Appointment>> getPendingAppointments(@PathVariable String consultantEmail) {
        List<Appointment> pendingAppointments = appointmentService.getPendingAppointmentsForConsultant(consultantEmail);
        return new ResponseEntity<>(pendingAppointments, HttpStatus.OK);
    }

    // Update the status of an appointment (ACCEPTED or REJECTED)
    @PutMapping("/consultant/{consultantEmail}/status")
    public ResponseEntity<Appointment> updateAppointmentStatus(@PathVariable String consultantEmail, @RequestBody AppointmentStatus status) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointmentStatusByEmail(consultantEmail, status);
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Return 404 if no appointment is found
        }
    }
}
