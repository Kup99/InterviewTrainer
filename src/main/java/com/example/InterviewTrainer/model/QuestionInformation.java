package com.example.InterviewTrainer.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class QuestionInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bundle_id")
    @NotNull
    private Long bundle_id;
    private Long user_id;
    private Long question_id;

    private String stepOfLearning;
}