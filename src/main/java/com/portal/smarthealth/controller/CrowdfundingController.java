package com.portal.smarthealth.controller;

import com.portal.smarthealth.dto.CrowdfundingRequest;
import com.portal.smarthealth.model.entity.Campaign;
import com.portal.smarthealth.model.entity.User;
import com.portal.smarthealth.service.CrowdfundingService;
import com.portal.smarthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crowdfunding")
public class CrowdfundingController {

    @Autowired
    private CrowdfundingService crowdfundingService;

    @Autowired
    private UserService userService;

    @PostMapping("/create-project")
    public ResponseEntity<?> createProject(@RequestBody CrowdfundingRequest crowdfundingRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User currentUser = userService.findByUsername(username)
                                         .orElseThrow(() -> new RuntimeException("Authenticated user not found."));

            Campaign newProject = crowdfundingService.createProject(
                    currentUser.getId(),
                    crowdfundingRequest.getProjectName(),
                    crowdfundingRequest.getDescription(),
                    crowdfundingRequest.getTargetAmount()
            );
            return new ResponseEntity<>(newProject, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the project.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Campaign>> getAllProjects() {
        List<Campaign> projects = crowdfundingService.getAllActiveProjects();
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/contribute/{projectId}")
    public ResponseEntity<?> contributeToProject(@PathVariable Long projectId, @RequestParam Double amount) {
        try {
            Campaign updatedProject = crowdfundingService.contributeToProject(projectId, amount);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred during contribution.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}