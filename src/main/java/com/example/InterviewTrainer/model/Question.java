package com.example.InterviewTrainer.model;

import com.example.InterviewTrainer.questionEnums.QuestionGroups;
import com.example.InterviewTrainer.questionEnums.QuestionLevel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private QuestionLevel questionLevel;
    private String questionName;
    private String questionDescription;
    @Enumerated(EnumType.STRING)
    QuestionGroups questionGroup;

}

