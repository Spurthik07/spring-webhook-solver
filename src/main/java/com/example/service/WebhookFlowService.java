package com.example.webhookapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebhookFlowService implements ApplicationRunner {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${hiring.identity.name}")
    private String name;

    @Value("${hiring.identity.regNo}")
    private String regNo;

    @Value("${hiring.identity.email}")
    private String email;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("🚀 Starting Webhook App...");

        // Step 1️⃣ - Call the generateWebhook API
        String generateWebhookUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        String body = String.format("{\"name\": \"%s\", \"regNo\": \"%s\", \"email\": \"%s\"}", name, regNo, email);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(generateWebhookUrl, entity, String.class);

        System.out.println("✅ Webhook generated response:");
        System.out.println(response.getBody());

        // Step 2️⃣ - Parse the webhook URL and accessToken from JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.getBody());
        String webhookUrl = jsonNode.get("webhook").asText();
        String accessToken = jsonNode.get("accessToken").asText();

        System.out.println("\n📬 Webhook URL: " + webhookUrl);
        System.out.println("🔑 Access Token: " + accessToken);

        // Step 3️⃣ - Prepare your final SQL query
        // 👇 Replace this query with your actual SQL solution
        String finalSQLQuery = "SELECT department, COUNT(*) FROM employees GROUP BY department;";


        // Step 4️⃣ - Send your SQL query to the webhook using the accessToken
        HttpHeaders postHeaders = new HttpHeaders();
        postHeaders.setContentType(MediaType.APPLICATION_JSON);
        // The instruction said: Authorization : <accessToken> (no Bearer prefix)
        postHeaders.set("Authorization", accessToken);

        String postBody = String.format("{\"finalQuery\": \"%s\"}", finalSQLQuery);
        HttpEntity<String> postEntity = new HttpEntity<>(postBody, postHeaders);

        ResponseEntity<String> finalResponse = restTemplate.postForEntity(webhookUrl, postEntity, String.class);

        // Step 5️⃣ - Print the submission result
        System.out.println("\n📤 SQL Query submitted successfully!");
        System.out.println("Server Response:");
        System.out.println(finalResponse.getBody());

        System.out.println("\n🎉 Done! Your solution has been posted to the webhook successfully.");
    }
}
