package com.example.InterviewTrainer.service;


import com.example.InterviewTrainer.model.Question;
import com.example.InterviewTrainer.questionEnums.QuestionGroups;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionService {
    Question getQuestionByName(String questionName);

    void saveAll(Question Question);

    List<Question> getAllQuestions();

    Optional<Question> getQuestion(Long questionId);

    Set<Long> getAllUserQuestionsId(Long userId) throws SQLException;

    Set<Question> getOnStudyUserQuestions(Long userId);

    Set<Question> getStudiedUserQuestions(Long userId);

    Set<Question> getAllUserQuestionsByQuestionIds(Set<Long> ids);

    Set<Question> getAllQuestionsByQuestionGroup(QuestionGroups questionGroup);
}
