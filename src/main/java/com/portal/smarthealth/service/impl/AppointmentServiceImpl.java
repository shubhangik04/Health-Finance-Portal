package com.portal.smarthealth.service.impl;

import com.portal.smarthealth.model.entity.Appointment;
import com.portal.smarthealth.model.entity.User;
import com.portal.smarthealth.repository.AppointmentRepository;
import com.portal.smarthealth.service.AppointmentService;
import com.portal.smarthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    @Override
    public Appointment scheduleAppointment(Long userId, String specialty, LocalDate date, LocalTime time) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setSpecialty(specialty);
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointment.setStatus("PENDING");

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getUserAppointments(Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return appointmentRepository.findByUserOrderByAppointmentDateDescAppointmentTimeDesc(user);
    }
}