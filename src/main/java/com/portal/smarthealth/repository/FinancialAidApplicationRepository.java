package com.portal.smarthealth.repository;

import com.portal.smarthealth.model.entity.FinancialAidApplication;
import com.portal.smarthealth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialAidApplicationRepository extends JpaRepository<FinancialAidApplication, Long> {
    Optional<FinancialAidApplication> findByUser(User user);
    List<FinancialAidApplication> findByUserOrderByIdDesc(User user);
}