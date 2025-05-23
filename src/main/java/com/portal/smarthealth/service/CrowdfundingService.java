package com.portal.smarthealth.service;

import com.portal.smarthealth.model.entity.Campaign;
import java.util.List;
import java.util.Optional;

public interface CrowdfundingService {
    Campaign createProject(Long userId, String projectName, String description, Double targetAmount);
    List<Campaign> getAllActiveProjects();
    Optional<Campaign> getProjectById(Long projectId);
    Campaign contributeToProject(Long projectId, Double amount);
}