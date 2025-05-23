package com.portal.smarthealth.controller;

import com.portal.smarthealth.model.entity.HealthRecord;
import com.portal.smarthealth.model.entity.User;
import com.portal.smarthealth.service.HealthRecordService;
import com.portal.smarthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class HealthRecordsController {

    @Autowired
    private HealthRecordService healthRecordService;

    @Autowired
    private UserService userService;

    @GetMapping("/my-records")
    public ResponseEntity<?> getUserHealthRecords() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User currentUser = userService.findByUsername(username)
                                         .orElseThrow(() -> new RuntimeException("Authenticated user not found."));

            List<HealthRecord> records = healthRecordService.getUserHealthRecords(currentUser.getId());
            return ResponseEntity.ok(records);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching health records.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}