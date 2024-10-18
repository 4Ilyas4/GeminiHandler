package org.example.geminihandler.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class GeminiService {

    private final RestTemplate restTemplate;
    private final String apiUrlTemplate;

    public GeminiService(RestTemplate restTemplate, @Value("${gemini.api.url}") String apiUrlTemplate) {
        this.restTemplate = restTemplate;
        this.apiUrlTemplate = apiUrlTemplate;
    }

    public String callApi(String prompt, String geminiKey) {
        String apiUrl = String.format(apiUrlTemplate, geminiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode contentNode = objectMapper.createObjectNode();
        ObjectNode partsNode = objectMapper.createObjectNode();
        partsNode.put("text", prompt);
        contentNode.set("parts", objectMapper.createArrayNode().add(partsNode));
        ObjectNode requestBodyNode = objectMapper.createObjectNode();
        requestBodyNode.set("contents", objectMapper.createArrayNode().add(contentNode));

        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(requestBodyNode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to construct JSON request body", e);
        }

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            throw new RuntimeException("API call failed: " + e.getMessage(), e);
        }

        if (response.getBody() == null) {
            throw new IllegalStateException("No response received from API");
        }

        return response.getBody();
    }
}
