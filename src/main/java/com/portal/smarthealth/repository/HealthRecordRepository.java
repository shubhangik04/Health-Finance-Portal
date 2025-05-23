package com.portal.smarthealth.repository;

import com.portal.smarthealth.model.entity.HealthRecord;
import com.portal.smarthealth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByUserOrderByIdDesc(User user);
}