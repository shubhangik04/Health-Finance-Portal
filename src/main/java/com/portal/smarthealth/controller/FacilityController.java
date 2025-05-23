package com.portal.smarthealth.controller;

import com.portal.smarthealth.model.entity.Facility;
import com.portal.smarthealth.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @PostConstruct
    public void initFacilities() {
        if (facilityService.getAllFacilities().isEmpty()) {
            Facility f1 = new Facility();
            f1.setName("Central Pune Doctor House");
            f1.setAddress("G1B, The Metropole, Central Pune Doctor House, Bund Garden Rd, next to Inox theatre, Agarkar Nagar, Pune, Maharashtra 411001, India");
            f1.setMapUrl("https://maps.google.com/?cid=3474177593645843822");
            f1.setPhoneNumber("+91 90902 04747");
            f1.setType("Clinic");
            f1.setLatitude(18.5284);
            f1.setLongitude(73.8744);
            facilityService.addFacility(f1);

            Facility f2 = new Facility();
            f2.setName("PrognoHealth Solutions India Pvt Ltd.");
            f2.setAddress("D44, RAHUL COMPLEX, Paud Rd, next to Krishna Hospital, Prashant Society, Kothrud, Pune, Maharashtra 411038, India");
            f2.setMapUrl("https://maps.google.com/?cid=6346526806628685809");
            f2.setType("Diagnostic Center");
            f2.setLatitude(18.5085);
            f2.setLongitude(73.8058);
            facilityService.addFacility(f2);

            Facility f3 = new Facility();
            f3.setName("MJM Hospital");
            f3.setAddress("Janardan Sadan, 1194, 1194/23, Ghole Rd, Sumukh Society, Sud Nagar, Shivajinagar, Pune, Maharashtra 411005, India");
            f3.setMapUrl("https://maps.google.com/?cid=6215983548146607698");
            f3.setPhoneNumber("+91 20 2553 5869");
            f3.setType("Hospital");
            f3.setLatitude(18.5273);
            f3.setLongitude(73.8488);
            facilityService.addFacility(f3);

            Facility f4 = new Facility();
            f4.setName("Jehangir Hospital");
            f4.setAddress("32, Sasoon Rd, opposite Railway Station, Central Excise Colony, Sangamvadi, Pune, Maharashtra 411001, India");
            f4.setMapUrl("https://maps.google.com/?cid=18345627783870603539");
            f4.setPhoneNumber("+91 20 6681 9999");
            f4.setType("Hospital");
            f4.setLatitude(18.5246);
            f4.setLongitude(73.8703);
            facilityService.addFacility(f4);

            Facility f5 = new Facility();
            f5.setName("Joshi Hospital, Maharashtra Medical Foundation");
            f5.setAddress("778, Kamala Nehru Park Rd, opposite Kamala Nehru Park, Shivajinagar, Pune, Maharashtra 411004, India");
            f5.setMapUrl("https://maps.google.com/?cid=16197127028439024394");
            f5.setPhoneNumber("+91 20 4109 6666");
            f5.setType("Hospital");
            f5.setLatitude(18.5191);
            f5.setLongitude(73.8447);
            facilityService.addFacility(f5);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Facility>> getAllFacilities() {
        List<Facility> facilities = facilityService.getAllFacilities();
        return ResponseEntity.ok(facilities);
    }
}