package com.example.InterviewTrainer.model;

import com.example.InterviewTrainer.taskEnums.TaskGroups;
import com.example.InterviewTrainer.taskEnums.TaskLevel;

import javax.persistence.*;

@Entity
@Table
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TaskLevel taskLevel;
    private String taskName;
    private String taskDescription;
    @Enumerated(EnumType.STRING)
    TaskGroups taskGroup;

    public Task() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskLevel getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(TaskLevel taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskGroups getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroups taskGroup) {
        this.taskGroup = taskGroup;
    }

}
