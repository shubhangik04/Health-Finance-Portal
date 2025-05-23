package com.portal.smarthealth.service.impl;

import com.portal.smarthealth.model.entity.FinancialAidApplication;
import com.portal.smarthealth.model.entity.User;
import com.portal.smarthealth.repository.FinancialAidApplicationRepository;
import com.portal.smarthealth.service.FinancialAidService;
import com.portal.smarthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FinancialAidServiceImpl implements FinancialAidService {

    @Autowired
    private FinancialAidApplicationRepository financialAidApplicationRepository;

    @Autowired
    private UserService userService;

    @Override
    public FinancialAidApplication submitApplication(Long userId, Integer age, Double income, Integer familySize, String medicalCondition) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Optional<FinancialAidApplication> existingApplication = financialAidApplicationRepository.findByUser(user);
        if (existingApplication.isPresent() && existingApplication.get().getStatus().equals("PENDING")) {
            throw new RuntimeException("User already has a pending financial aid application.");
        }

        FinancialAidApplication application = new FinancialAidApplication();
        application.setUser(user);
        application.setAge(age);
        application.setIncome(income);
        application.setFamilySize(familySize);
        application.setMedicalCondition(medicalCondition);
        application.setSubmissionDate(LocalDateTime.now());
        application.setStatus("PENDING");

        List<String> eligiblePrograms = new ArrayList<>();
        if (age != null && age >= 60 && income != null && income <= 30000) {
            eligiblePrograms.add("Senior Citizen Health Support Program");
        }
        if (familySize != null && familySize >= 4 && income != null && income <= 50000) {
            eligiblePrograms.add("Large Family Medical Assistance");
        }
        if (medicalCondition != null && medicalCondition.toLowerCase().contains("cancer") && income != null && income <= 75000) {
            eligiblePrograms.add("Cancer Care Financial Aid");
        }
        if (income != null && income <= 20000) {
            eligiblePrograms.add("Low-Income Healthcare Grant");
        }

        application.setEligiblePrograms(String.join(", ", eligiblePrograms));
        if (!eligiblePrograms.isEmpty()) {
            application.setStatus("ELIGIBLE");
        } else {
            application.setStatus("NOT_ELIGIBLE");
        }

        return financialAidApplicationRepository.save(application);
    }

    @Override
    public List<FinancialAidApplication> getUserApplications(Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return financialAidApplicationRepository.findByUserOrderByIdDesc(user);
    }
}