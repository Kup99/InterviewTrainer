package com.example.InterviewTrainer.controller;


import com.example.InterviewTrainer.model.Task;
import com.example.InterviewTrainer.model.User;
import com.example.InterviewTrainer.service.TaskService;
import com.example.InterviewTrainer.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;


@Controller
public class UserController {
    private static final Gson gson = new Gson();
    private static Logger logger = Logger.getLogger(String.valueOf(UserController.class));

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userName}")
    public ResponseEntity getUserByUserName(@PathVariable("userName") String userName) {
        User user = userService.getUserByName(userName);
        return new ResponseEntity(gson.toJson(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ResponseEntity getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity(gson.toJson(users), HttpStatus.OK);
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(User user) {
        try {
            if (userService.getUserByName(user.getUserName()) != null) {
                throw new SQLException("User already exist " + user.getUserName());
            }

        } catch (SQLException ex) {
            logger.warning("User already exist " + user.getUserName());
        }
//        if (!user.getPassword().equals(user.getConfirmPassword())) {
//            throw new Exception("Password mismatch");
        userService.saveAll(user);

        return "redirect:/";
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        userService.deleteById(id);
        return new ResponseEntity(gson.toJson(user), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addTaskToUser/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity addTaskToUserById(@RequestBody Task task, @PathVariable("id") Long id, BindingResult bindingResult) throws Exception {
        String jsonTask = gson.toJson(task, Task.class);
        if (taskService.getTaskByName(task.getTaskName()) == null) {
            taskService.saveAll(task);
        }
        Long taskId = taskService.getTaskByName(task.getTaskName()).getId();
        if (userService.getUserById(id).isEmpty()) {
            throw new SQLException("User not found");
        }
        if (taskService.getAllUserTaskId(id).stream().filter(x -> x.equals(taskId)).findAny().isEmpty()) {
            userService.addTaskToUser(id, taskId);
        }


        return new ResponseEntity(gson.toJson(jsonTask), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user_tasks/{id}")
    public String getUserTasksByUserId(@PathVariable("id") Long userId, Model model) {
        Set<Long> taskIds = null;
        try {
            taskIds = taskService.getAllUserTaskId(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Set<Task> tasks = taskService.getAllUserTasksByTaskIds(taskIds);
        model.addAttribute("user_tasks", tasks);
        return "user_tasks";
    }

}
