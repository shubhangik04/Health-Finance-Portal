package com.portal.smarthealth.repository;

import com.portal.smarthealth.model.entity.Appointment;
import com.portal.smarthealth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserOrderByAppointmentDateDescAppointmentTimeDesc(User user);
}