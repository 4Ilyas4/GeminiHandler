package org.example.geminihandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeminiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeminiApplication.class, args);
    }
}
