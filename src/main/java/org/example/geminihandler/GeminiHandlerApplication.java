package org.example.geminihandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeminiHandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeminiHandlerApplication.class, args);
    }
}
