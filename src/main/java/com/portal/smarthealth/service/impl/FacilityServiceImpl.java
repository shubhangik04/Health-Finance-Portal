package com.portal.smarthealth.service.impl;

import com.portal.smarthealth.model.entity.Facility;
import com.portal.smarthealth.repository.FacilityRepository;
import com.portal.smarthealth.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public Optional<Facility> getFacilityById(Long id) {
        return facilityRepository.findById(id);
    }

    @Override
    public Facility addFacility(Facility facility) {
        return facilityRepository.save(facility);
    }
}