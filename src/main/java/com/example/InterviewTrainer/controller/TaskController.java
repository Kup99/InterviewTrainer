package com.example.InterviewTrainer.controller;

import com.example.InterviewTrainer.model.Task;
import com.example.InterviewTrainer.service.TaskService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


@RestController
public class TaskController {
    private static final Gson gson = new Gson();
    private static Logger logger = Logger.getLogger(String.valueOf(TaskController.class));

    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.POST, value = "/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity addTask(@RequestBody Task task, BindingResult bindingResult) throws Exception {
        String jsonTask = gson.toJson(task, Task.class);
        if (taskService.getTaskByName(task.getTaskName()) != null) {
            throw new Exception("Task already exists");
        }

        if (bindingResult.hasErrors()) {
            logger.warning("Some wrong with post");
            throw new BindException(bindingResult);
        }
        taskService.saveAll(task);
        return new ResponseEntity(gson.toJson(jsonTask), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public ResponseEntity getTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity(gson.toJson(tasks), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user_study_tasks/{id}")
    public ResponseEntity getOnStudyUserTasks(@PathVariable("id") Long userId) {
        Set<Task> tasks = taskService.getOnStudyUserTasks(userId);
        return new ResponseEntity(gson.toJson(tasks), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user_known_tasks/{id}")
    public ResponseEntity getStudiedUserTasks(@PathVariable("id") Long userId) {
        Set<Task> tasks = taskService.getStudiedUserTasks(userId);
        return new ResponseEntity(gson.toJson(tasks), HttpStatus.OK);
    }
}
