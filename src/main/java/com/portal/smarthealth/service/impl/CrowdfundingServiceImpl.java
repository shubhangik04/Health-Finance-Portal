package com.portal.smarthealth.service.impl;

import com.portal.smarthealth.model.entity.Campaign;
import com.portal.smarthealth.model.entity.User;
import com.portal.smarthealth.repository.CampaignRepository;
import com.portal.smarthealth.service.CrowdfundingService;
import com.portal.smarthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CrowdfundingServiceImpl implements CrowdfundingService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private UserService userService;

    @Override
    public Campaign createProject(Long userId, String projectName, String description, Double targetAmount) {
        User creator = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Campaign project = new Campaign();
        project.setCreator(creator);
        project.setCampaignName(projectName);
        project.setDescription(description);
        project.setTargetAmount(targetAmount);
        project.setRaisedAmount(0.0);
        project.setCreationDate(LocalDateTime.now());
        project.setStatus("ACTIVE");

        return campaignRepository.save(project);
    }

    @Override
    public List<Campaign> getAllActiveProjects() {
        return campaignRepository.findByStatus("ACTIVE");
    }

    @Override
    public Optional<Campaign> getProjectById(Long projectId) {
        return campaignRepository.findById(projectId);
    }

    @Override
    public Campaign contributeToProject(Long projectId, Double amount) {
        Campaign project = campaignRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

        if (project.getStatus().equals("ACTIVE")) {
            project.setRaisedAmount(project.getRaisedAmount() + amount);
            if (project.getRaisedAmount() >= project.getTargetAmount()) {
                project.setStatus("FUNDED");
            }
            return campaignRepository.save(project);
        } else {
            throw new RuntimeException("Project is not active for contributions.");
        }
    }
}