package com.example.InterviewTrainer.service.impl;

import com.example.InterviewTrainer.configuration.DatabaseConnection;
import com.example.InterviewTrainer.model.Question;
import com.example.InterviewTrainer.model.Question;
import com.example.InterviewTrainer.questionEnums.QuestionGroups;
import com.example.InterviewTrainer.repository.QuestionInformationRepository;
import com.example.InterviewTrainer.repository.QuestionRepository;
import com.example.InterviewTrainer.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionInformationRepository questionInformationRepository;
    @Autowired
    DatabaseConnection databaseConnection;

    @Override
    public Question getQuestionByName(String questionName) {
        return questionRepository.findByQuestionName(questionName);
    }

    @Override
    public void saveAll(Question question) {
        questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestion(Long questionId) {
        return questionRepository.findById(questionId);
    }

    @Override
    public Set<Long> getAllUserQuestionsId(Long userId) {
        return questionInformationRepository.getAllUserQuestionsId(userId);
    }


    @Override
    public Set<Question> getOnStudyUserQuestions(Long userId) {
        return getAllUserQuestionsByQuestionIds(questionInformationRepository.getOnStudyUserQuestionId(userId));
    }

    @Override
    public Set<Question> getStudiedUserQuestions(Long userId) {
        return getAllUserQuestionsByQuestionIds(questionInformationRepository.getStudiedUserQuestionId(userId));
    }


    @Override
    public Set<Question> getAllUserQuestionsByQuestionIds(Set<Long> ids) {
        return ids.stream().map(id -> getQuestion(id).get()).collect(Collectors.toSet());
    }

    @Override
    public Set<Question> getAllQuestionsByQuestionGroup(QuestionGroups questionGroup) {
       return questionRepository.getAllQuestionsByQuestionGroup(questionGroup);
    }


}
