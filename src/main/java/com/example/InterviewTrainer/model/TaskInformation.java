package com.example.InterviewTrainer.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class TaskInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bundle_id")
    @NotNull
    private Long bundle_id;
    private Long user_id;
    private Long task_id;

    private String stepOfLearning;

    public TaskInformation() {
    }

    public Long getBundle_id() {
        return bundle_id;
    }

    public void setBundle_id(Long bundle_id) {
        this.bundle_id = bundle_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public String getStepOfLearning() {
        return stepOfLearning;
    }

    public void setStepOfLearning(String stepOfLearning) {
        this.stepOfLearning = stepOfLearning;
    }
}
