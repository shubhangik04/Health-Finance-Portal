package com.portal.smarthealth.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "financial_aid_applications")
public class FinancialAidApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer age;
    private Double income;
    private Integer familySize;
    @Lob
    private String medicalCondition;
    private LocalDateTime submissionDate;
    private String status; // e.g., "PENDING", "ELIGIBLE", "NOT_ELIGIBLE", "REVIEWED"
    @Lob
    private String eligiblePrograms;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getFamilySize() {
        return familySize;
    }

    public void setFamilySize(Integer familySize) {
        this.familySize = familySize;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEligiblePrograms() {
        return eligiblePrograms;
    }

    public void setEligiblePrograms(String eligiblePrograms) {
        this.eligiblePrograms = eligiblePrograms;
    }
}