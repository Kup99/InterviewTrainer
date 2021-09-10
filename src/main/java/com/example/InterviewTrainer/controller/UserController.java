package com.example.InterviewTrainer.controller;


import com.example.InterviewTrainer.model.*;
import com.example.InterviewTrainer.service.QuestionService;
import com.example.InterviewTrainer.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    QuestionService questionService;

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


    @RequestMapping(value = "/auth/registration", method = RequestMethod.POST)
    public String addUser(User user) {
        try {
            if (userService.getUserByName(user.getFirstName()) != null) {
                throw new SQLException("User already exist " + user.getFirstName());
            }

        } catch (SQLException ex) {
            logger.warning("User already exist " + user.getFirstName());
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "redirect:/auth/registration";
        }
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userService.saveAll(user);

        return "redirect:/auth/login";
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        userService.deleteById(id);
        return new ResponseEntity(gson.toJson(user), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addQuestionToUser/{id}", consumes = "application/json", produces = "application/json")
    public String addQuestionToUserById(@RequestBody Question question, @PathVariable("id") Long id, BindingResult bindingResult) throws Exception {
        if (questionService.getQuestionByName(question.getQuestionName()) == null) {
            questionService.saveAll(question);
        }
        Long questionId = questionService.getQuestionByName(question.getQuestionName()).getId();
        if (userService.getUserById(id).isEmpty()) {
            throw new SQLException("User not found");
        }
        if (questionService.getAllUserQuestionsId(id).stream().filter(x -> x.equals(questionId)).findAny().isEmpty()) {
            userService.addQuestionToUser(id, questionId);
        }


        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user_questions/{id}")
    public String getUserQuestionByUserId(@PathVariable("id") Long userId, Model model) {
        Set<Long> questionsIds = null;
        try {
            questionsIds = questionService.getAllUserQuestionsId(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Set<Question> questions = questionService.getAllUserQuestionsByQuestionIds(questionsIds);
        model.addAttribute("user_questions", questions);
        return "user_questions";
    }

}
