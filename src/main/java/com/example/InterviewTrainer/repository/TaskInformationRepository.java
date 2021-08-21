package com.example.InterviewTrainer.repository;

import com.example.InterviewTrainer.model.TaskInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskInformationRepository extends JpaRepository<TaskInformation, Long>, JpaSpecificationExecutor<TaskInformation> {
    @Query("SELECT t.task_id FROM TaskInformation t WHERE t.user_id= :userId")
    Set<Long> getAllUserTaskId(Long userId);

    @Query("SELECT t.task_id FROM TaskInformation t WHERE t.user_id= :userId AND t.stepOfLearning='ON_STUDY'")
    Set<Long> getOnStudyUserTasksId(Long userId);

    @Query("SELECT t.task_id FROM TaskInformation t WHERE t.user_id= :userId AND t.stepOfLearning='STUDIED'")
    Set<Long> getStudiedUserTasksId(Long userId);
}
