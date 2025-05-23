package com.portal.smarthealth.service;

import com.portal.smarthealth.model.entity.FinancialAidApplication;
import java.util.List;

public interface FinancialAidService {
    FinancialAidApplication submitApplication(Long userId, Integer age, Double income, Integer familySize, String medicalCondition);
    List<FinancialAidApplication> getUserApplications(Long userId);
}