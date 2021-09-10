package com.example.InterviewTrainer.service;


import com.example.InterviewTrainer.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUserByName(String firstName);

    void saveAll(User user);

    void deleteById(Long id);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    void addQuestionToUser(Long userId, Long questionId) throws SQLException;
}
