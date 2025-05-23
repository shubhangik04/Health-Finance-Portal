package com.portal.smarthealth.controller;

import com.portal.smarthealth.dto.AppointmentRequest;
import com.portal.smarthealth.model.entity.Appointment;
import com.portal.smarthealth.model.entity.User;
import com.portal.smarthealth.service.AppointmentService;
import com.portal.smarthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telemedicine")
public class TelemedicineController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User currentUser = userService.findByUsername(username)
                                         .orElseThrow(() -> new RuntimeException("Authenticated user not found."));

            Appointment scheduledAppointment = appointmentService.scheduleAppointment(
                    currentUser.getId(),
                    appointmentRequest.getSpecialty(),
                    appointmentRequest.getAppointmentDate(),
                    appointmentRequest.getAppointmentTime()
            );
            return new ResponseEntity<>(scheduledAppointment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while scheduling the appointment.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/my-appointments")
    public ResponseEntity<?> getUserAppointments() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User currentUser = userService.findByUsername(username)
                                         .orElseThrow(() -> new RuntimeException("Authenticated user not found."));

            List<Appointment> appointments = appointmentService.getUserAppointments(currentUser.getId());
            return ResponseEntity.ok(appointments);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching appointments.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}