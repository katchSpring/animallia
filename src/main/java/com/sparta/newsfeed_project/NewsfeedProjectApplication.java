package com.sparta.newsfeed_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class NewsfeedProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsfeedProjectApplication.class, args);
    }

}
