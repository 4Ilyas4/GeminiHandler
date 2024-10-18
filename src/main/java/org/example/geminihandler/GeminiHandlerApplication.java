package org.example.geminihandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class GeminiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeminiApplication.class, args);
    }
}
