package com.example.InterviewTrainer.controller;

import com.example.InterviewTrainer.model.Question;
import com.example.InterviewTrainer.service.QuestionService;
import com.example.InterviewTrainer.questionEnums.QuestionGroups;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


@Controller
@RequestMapping("/question")
public class QuestionController {
    private static final Gson gson = new Gson();
    private static Logger logger = Logger.getLogger(String.valueOf(QuestionController.class));

    @Autowired
    QuestionService questionService;


    @RequestMapping(method = RequestMethod.GET, value = "/study_questions")
    public String getOnStudyUserQuestions(Model model) {
        Set<Question> questions = questionService.getOnStudyUserQuestions(1L);
        model.addAttribute("studyQuestions", questions);
        return "on_study_questions";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/studied_questions")
    public String getStudiedUserQuestions(Model model) {
        Set<Question> questions = questionService.getStudiedUserQuestions(1L);
        model.addAttribute("studiedQuestions", questions);
        return "studied";
    }

    @GetMapping("/getQuestions")
    public String getQuestionPage(Model model) throws SQLException {
        Set<Long> questionsIds = questionService.getAllUserQuestionsId(1L);
        Set<Question> questions = questionService.getAllUserQuestionsByQuestionIds(questionsIds);
        model.addAttribute("questions", questions);
        return "questions";
    }

    @GetMapping("/getAllQuestionsGroups")
    public String getAllQuestionGroups(Model model) throws SQLException {
        List<Enum> questionsGroups = Arrays.asList(QuestionGroups.values());
        model.addAttribute("questionsGroups", questionsGroups);
        return "allquestionsgroups";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllQuestionsByQuestionGroup/{questionGroup}")
    public String getAllQuestionByQuestionGroup(@PathVariable("questionGroup") QuestionGroups questionGroup, Model model) {
        model.addAttribute("questions", questionService.getAllQuestionsByQuestionGroup(questionGroup));
        return "questions";
    }
}
