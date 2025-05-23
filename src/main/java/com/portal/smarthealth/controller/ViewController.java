package com.portal.smarthealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index"; // Corresponds to index.jsp
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Corresponds to register.jsp
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Corresponds to login.jsp
    }

    @GetMapping("/facilities")
    public String facilities() {
        return "facilities"; // Corresponds to facilities.jsp
    }

    @GetMapping("/financial")
    public String financial() {
        return "financial"; // Corresponds to financial.jsp
    }

    @GetMapping("/telemedicine")
    public String telemedicine() {
        return "telemedicine"; // Corresponds to telemedicine.jsp
    }

    @GetMapping("/crowdfunding")
    public String crowdfunding() {
        return "crowdfunding"; // Corresponds to crowdfunding.jsp
    }

    @GetMapping("/records")
    public String records() {
        return "records"; // Corresponds to records.jsp
    }

    @GetMapping("/tips")
    public String healthTips() {
        return "health-tips"; // Corresponds to health-tips.jsp
    }
}