package com.example.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
public class InterviewApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext interviewApplication = SpringApplication.run(InterviewApplication.class, args);
        System.out.println(interviewApplication.getBean(Account.class).getCurrency());
    }


}
