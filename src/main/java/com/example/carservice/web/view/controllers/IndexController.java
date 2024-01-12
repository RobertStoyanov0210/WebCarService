package com.example.carservice.web.view.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {
    @GetMapping()
    public String getIndex(Model model){
        return "index";
    }

    @GetMapping("login")
    public String login(Model model) {
        final String welcomeMessage = "Login Form";
        model.addAttribute("welcome", welcomeMessage);
        return "login";
    }



    @GetMapping("unauthorized")
    public String unauthorized(Model model) {
//        final String welcomeMessage = "Welcome to the School Management System!";
//        model.addAttribute("welcome", welcomeMessage);
        return "unauthorized";
    }
}
