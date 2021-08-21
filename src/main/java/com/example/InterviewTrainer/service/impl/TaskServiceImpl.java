package com.example.InterviewTrainer.service.impl;

import com.example.InterviewTrainer.configuration.DatabaseConnection;
import com.example.InterviewTrainer.model.Task;
import com.example.InterviewTrainer.repository.TaskInformationRepository;
import com.example.InterviewTrainer.repository.TaskRepository;
import com.example.InterviewTrainer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskInformationRepository taskInformationRepository;
    @Autowired
    DatabaseConnection databaseConnection;

    @Override
    public Task getTaskByName(String taskName) {
        return taskRepository.findByTaskName(taskName);
    }

    @Override
    public void saveAll(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTask(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Set<Long> getAllUserTaskId(Long userId) {
        return taskInformationRepository.getAllUserTaskId(userId);
    }


    @Override
    public Set<Task> getOnStudyUserTasks(Long userId) {
        return getAllUserTasksByTaskIds(taskInformationRepository.getOnStudyUserTasksId(userId));
    }

    @Override
    public Set<Task> getStudiedUserTasks(Long userId) {
        return getAllUserTasksByTaskIds(taskInformationRepository.getStudiedUserTasksId(userId));
    }


    @Override
    public Set<Task> getAllUserTasksByTaskIds(Set<Long> ids) {
        return ids.stream().map(id -> getTask(id).get()).collect(Collectors.toSet());
    }


}
