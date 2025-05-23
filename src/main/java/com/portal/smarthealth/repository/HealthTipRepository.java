package com.portal.smarthealth.repository;

import com.portal.smarthealth.model.entity.HealthTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthTipRepository extends JpaRepository<HealthTip, Long> {
    List<HealthTip> findByCategory(String category);
    List<HealthTip> findByOrderByPublicationDateDesc();
}