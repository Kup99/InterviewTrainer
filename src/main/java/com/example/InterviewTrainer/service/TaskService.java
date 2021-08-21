package com.example.InterviewTrainer.service;


import com.example.InterviewTrainer.model.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskService {
    Task getTaskByName(String taskName);

    void saveAll(Task task);

    List<Task> getAllTasks();

    Optional<Task> getTask(Long taskId);

    Set<Long> getAllUserTaskId(Long userId) throws SQLException;

    Set<Task> getOnStudyUserTasks(Long userId);

    Set<Task> getStudiedUserTasks(Long userId);

    Set<Task> getAllUserTasksByTaskIds(Set<Long> ids);
}
