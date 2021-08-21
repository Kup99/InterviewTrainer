package com.example.InterviewTrainer.controller;

import com.example.InterviewTrainer.model.Task;
import com.example.InterviewTrainer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class MainController {
    @Autowired
    TaskService taskService;

    @GetMapping(value = "/")
    public String getMainPage(Model model) {
        Set<Task> tasks = taskService.getOnStudyUserTasks(1L);
        model.addAttribute("user_tasks_on_study",tasks);
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        return "registrate";
    }
}

