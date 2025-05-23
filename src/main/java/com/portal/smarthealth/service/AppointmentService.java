package com.portal.smarthealth.service;

import com.portal.smarthealth.model.entity.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {
    Appointment scheduleAppointment(Long userId, String specialty, LocalDate date, LocalTime time);
    List<Appointment> getUserAppointments(Long userId);
}