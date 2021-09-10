package com.example.InterviewTrainer.repository;

import com.example.InterviewTrainer.model.Question;
import com.example.InterviewTrainer.questionEnums.QuestionGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    @Query("SELECT t FROM Question t WHERE t.questionName= :name")
    Question findByQuestionName(String name);

    @Query("SELECT t from Question t where t.questionGroup= :questionGroup")
    Set<Question> getAllQuestionsByQuestionGroup(QuestionGroups questionGroup);


}
