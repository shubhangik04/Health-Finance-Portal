package com.portal.smarthealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.portal.smarthealth.*"})
@EntityScan(basePackages = {"com.portal.smarthealth.model.entity"})
@EnableJpaRepositories(basePackages = {"com.portal.smarthealth.repository"})
public class SmartHealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHealthApplication.class, args);
    }
}