package com.portal.smarthealth.controller;

import com.portal.smarthealth.model.entity.HealthTip;
import com.portal.smarthealth.service.HealthTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/health-tips")
public class HealthTipsController {

    @Autowired
    private HealthTipService healthTipService;

    @PostConstruct
    public void initHealthTips() {
        if (healthTipService.getAllHealthTips().isEmpty()) {
            HealthTip tip1 = new HealthTip();
            tip1.setTitle("Stay Hydrated: Drink Enough Water");
            tip1.setContent("Drinking adequate water helps maintain bodily functions, regulates body temperature, and keeps your skin healthy. Aim for 8 glasses a day.");
            tip1.setPublicationDate(LocalDate.of(2023, 1, 15));
            tip1.setCategory("Nutrition");
            tip1.setAuthor("Dr. Wellness");
            healthTipService.addHealthTip(tip1);

            HealthTip tip2 = new HealthTip();
            tip2.setTitle("Benefits of Regular Exercise");
            tip2.setContent("Regular physical activity strengthens your heart, improves mood, boosts energy, and helps manage weight. Find an activity you enjoy!");
            tip2.setPublicationDate(LocalDate.of(2023, 2, 20));
            tip2.setCategory("Exercise");
            tip2.setAuthor("Fitness Expert");
            healthTipService.addHealthTip(tip2);

            HealthTip tip3 = new HealthTip();
            tip3.setTitle("Prioritize Sleep for Better Health");
            tip3.setContent("Adequate sleep is crucial for cognitive function, emotional well-being, and physical health. Aim for 7-9 hours per night.");
            tip3.setPublicationDate(LocalDate.of(2023, 3, 5));
            tip3.setCategory("Mental Health");
            tip3.setAuthor("Sleep Institute");
            healthTipService.addHealthTip(tip3);

            HealthTip tip4 = new HealthTip();
            tip4.setTitle("Healthy Eating Habits for a Strong Immune System");
            tip4.setContent("Incorporate a variety of fruits, vegetables, whole grains, and lean proteins into your diet to support your immune system.");
            tip4.setPublicationDate(LocalDate.of(2023, 4, 10));
            tip4.setCategory("Nutrition");
            tip4.setAuthor("Dietitian Pro");
            healthTipService.addHealthTip(tip4);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<HealthTip>> getAllHealthTips() {
        List<HealthTip> tips = healthTipService.getAllHealthTips();
        return ResponseEntity.ok(tips);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<HealthTip>> getHealthTipsByCategory(@PathVariable String category) {
        List<HealthTip> tips = healthTipService.getHealthTipsByCategory(category);
        return ResponseEntity.ok(tips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthTip> getHealthTipById(@PathVariable Long id) {
        return healthTipService.getHealthTipById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    // Consider adding @PreAuthorize("hasRole('ADMIN')") for security in a real application
    public ResponseEntity<HealthTip> addHealthTip(@RequestBody HealthTip healthTip) {
        HealthTip newTip = healthTipService.addHealthTip(healthTip);
        return new ResponseEntity<>(newTip, HttpStatus.CREATED);
    }
}