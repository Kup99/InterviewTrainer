package com.example.InterviewTrainer.controller;

import com.example.InterviewTrainer.model.User;
import com.example.InterviewTrainer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @GetMapping("/success")
    public String getSuccsessPage() {
        return "redirect:/auth/welcome";
    }


    @GetMapping("/welcome")
    public String getSuccessPage(Model model) {
        return "welcome";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registrate";
    }
}