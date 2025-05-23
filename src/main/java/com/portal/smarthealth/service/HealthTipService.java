package com.portal.smarthealth.service;

import com.portal.smarthealth.model.entity.HealthTip;
import java.util.List;
import java.util.Optional;

public interface HealthTipService {
    List<HealthTip> getAllHealthTips();
    List<HealthTip> getHealthTipsByCategory(String category);
    Optional<HealthTip> getHealthTipById(Long id);
    HealthTip addHealthTip(HealthTip healthTip);
}