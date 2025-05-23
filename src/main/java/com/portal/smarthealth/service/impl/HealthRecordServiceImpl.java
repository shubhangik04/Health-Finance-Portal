package com.portal.smarthealth.service.impl;

import com.portal.smarthealth.model.entity.HealthRecord;
import com.portal.smarthealth.model.entity.User;
import com.portal.smarthealth.repository.HealthRecordRepository;
import com.portal.smarthealth.service.HealthRecordService;
import com.portal.smarthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Autowired
    private UserService userService;

    @Override
    public HealthRecord addHealthRecord(Long userId, HealthRecord record) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        record.setUser(user);
        return healthRecordRepository.save(record);
    }

    @Override
    public List<HealthRecord> getUserHealthRecords(Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return healthRecordRepository.findByUserOrderByIdDesc(user);
    }

    @Override
    public Optional<HealthRecord> getHealthRecordById(Long recordId) {
        return healthRecordRepository.findById(recordId);
    }
}