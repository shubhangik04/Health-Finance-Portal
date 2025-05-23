package com.portal.smarthealth.service;

import com.portal.smarthealth.model.entity.HealthRecord;
import java.util.List;
import java.util.Optional;

public interface HealthRecordService {
    HealthRecord addHealthRecord(Long userId, HealthRecord record);
    List<HealthRecord> getUserHealthRecords(Long userId);
    Optional<HealthRecord> getHealthRecordById(Long recordId);
}