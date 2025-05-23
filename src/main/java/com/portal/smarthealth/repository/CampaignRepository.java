package com.portal.smarthealth.repository;

import com.portal.smarthealth.model.entity.Campaign;
import com.portal.smarthealth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByStatus(String status);
    List<Campaign> findByCreator(User creator);
}