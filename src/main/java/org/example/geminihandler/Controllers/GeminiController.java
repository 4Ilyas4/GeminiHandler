package org.example.geminihandler.Controllers;

import org.example.geminihandler.Services.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gemini")
public class GeminiController {

    private final GeminiService service;

    @Autowired
    public GeminiController(GeminiService service) {
        this.service = service;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generate(@RequestParam("prompt") String prompt, 
                                           @RequestParam("sessionId") String sessionId) {
        try {
            String response = service.callApi(prompt, sessionId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}

