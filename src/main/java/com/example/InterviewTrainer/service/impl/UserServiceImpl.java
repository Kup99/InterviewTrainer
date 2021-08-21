package com.example.InterviewTrainer.service.impl;


import com.example.InterviewTrainer.configuration.DatabaseConnection;
import com.example.InterviewTrainer.model.User;
import com.example.InterviewTrainer.repository.UserRepository;
import com.example.InterviewTrainer.service.UserService;
import com.example.InterviewTrainer.taskEnums.TaskLearningStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    DatabaseConnection databaseConnection;


    public UserServiceImpl() {
    }

    @Override
    public User getUserByName(String firstName) {
        return userRepository.findByUserName(firstName);
    }

    @Override
    public void saveAll(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public void addTaskToUser(Long userId, Long taskId) throws SQLException {
        PreparedStatement ps = databaseConnection.getConnection().prepareStatement("INSERT INTO task_information(user_id,task_id,step_of_learning) VALUES (?,?,?)");
        ps.setLong(1, userId);
        ps.setLong(2, taskId);
        ps.setString(3, TaskLearningStep.ON_STUDY.toString());
        ps.executeUpdate();
    }

}