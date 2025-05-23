package com.portal.smarthealth.service.impl;

import com.portal.smarthealth.model.entity.HealthTip;
import com.portal.smarthealth.repository.HealthTipRepository;
import com.portal.smarthealth.service.HealthTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HealthTipServiceImpl implements HealthTipService {

    @Autowired
    private HealthTipRepository healthTipRepository;

    @Override
    public List<HealthTip> getAllHealthTips() {
        return healthTipRepository.findByOrderByPublicationDateDesc();
    }

    @Override
    public List<HealthTip> getHealthTipsByCategory(String category) {
        return healthTipRepository.findByCategory(category);
    }

    @Override
    public Optional<HealthTip> getHealthTipById(Long id) {
        return healthTipRepository.findById(id);
    }

    @Override
    public HealthTip addHealthTip(HealthTip healthTip) {
        if (healthTip.getPublicationDate() == null) {
            healthTip.setPublicationDate(LocalDate.now());
        }
        return healthTipRepository.save(healthTip);
    }
}