package com.portal.smarthealth.controller;

import com.portal.smarthealth.dto.FinancialAidRequest;
import com.portal.smarthealth.model.entity.FinancialAidApplication;
import com.portal.smarthealth.model.entity.User;
import com.portal.smarthealth.service.FinancialAidService;
import com.portal.smarthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial-aid")
public class FinancialAidController {

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private UserService userService;

    @PostMapping("/submit-application")
    public ResponseEntity<?> submitApplication(@RequestBody FinancialAidRequest financialAidRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User currentUser = userService.findByUsername(username)
                                         .orElseThrow(() -> new RuntimeException("Authenticated user not found."));

            FinancialAidApplication application = financialAidService.submitApplication(
                    currentUser.getId(),
                    financialAidRequest.getAge(),
                    financialAidRequest.getIncome(),
                    financialAidRequest.getFamilySize(),
                    financialAidRequest.getMedicalCondition()
            );
            return new ResponseEntity<>(application, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while submitting the application.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/my-applications")
    public ResponseEntity<?> getUserApplications() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User currentUser = userService.findByUsername(username)
                                         .orElseThrow(() -> new RuntimeException("Authenticated user not found."));

            List<FinancialAidApplication> applications = financialAidService.getUserApplications(currentUser.getId());
            return ResponseEntity.ok(applications);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching applications.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}