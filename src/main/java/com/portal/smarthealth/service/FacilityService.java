package com.portal.smarthealth.service;

import com.portal.smarthealth.model.entity.Facility;
import java.util.List;
import java.util.Optional;

public interface FacilityService {
    List<Facility> getAllFacilities();
    Optional<Facility> getFacilityById(Long id);
    Facility addFacility(Facility facility);
}