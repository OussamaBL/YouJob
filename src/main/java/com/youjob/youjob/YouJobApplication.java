package com.youjob.youjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.youjob.youjob.repository")
public class YouJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouJobApplication.class, args);
    }

}
