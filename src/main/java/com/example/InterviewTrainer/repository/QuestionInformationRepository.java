package com.example.InterviewTrainer.repository;

import com.example.InterviewTrainer.model.QuestionInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionInformationRepository extends JpaRepository<QuestionInformation, Long>, JpaSpecificationExecutor<QuestionInformation> {
    @Query("SELECT t.question_id FROM QuestionInformation t WHERE t.user_id= :userId")
    Set<Long> getAllUserQuestionsId(Long userId);

    @Query("SELECT t.question_id FROM QuestionInformation t WHERE t.user_id= :userId AND t.stepOfLearning='ON_STUDY'")
    Set<Long> getOnStudyUserQuestionId(Long userId);

    @Query("SELECT t.question_id FROM QuestionInformation t WHERE t.user_id= :userId AND t.stepOfLearning='STUDIED'")
    Set<Long> getStudiedUserQuestionId(Long userId);
}
