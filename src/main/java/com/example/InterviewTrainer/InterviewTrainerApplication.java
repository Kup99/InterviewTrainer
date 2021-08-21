package com.example.InterviewTrainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.example.InterviewTrainer.repository")
@EntityScan("com.example.InterviewTrainer.model")
@SpringBootApplication(scanBasePackages = {"com.example.InterviewTrainer"})
public class InterviewTrainerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InterviewTrainerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(InterviewTrainerApplication.class, args);
    }

}

