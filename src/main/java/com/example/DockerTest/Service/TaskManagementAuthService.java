package com.example.DockerTest.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class TaskManagementAuthService {

    private final WebClient taskManagementClient;
    private String token;

    public TaskManagementAuthService(WebClient taskManagementClient) {
        this.taskManagementClient = taskManagementClient;
    }

    @Value("${task-management.login-url}")
    private String taskManagementLoginUrl;

    @Value("${task-management.userName}")
    private String userName;

    @Value("${task-management.password}")
    private String password;

    public void authenticate(){

        Map<String, String> credentials = Map.of("userName", userName, "password", password);

        this.token = taskManagementClient.post()
                .uri(taskManagementLoginUrl)
                .bodyValue(credentials)
                .retrieve()
                .bodyToMono(String.class).
                block();

    }

    public String retrieveToken(){

        if(token == null || token.isEmpty()) {
            authenticate();
        }

        return token;

    }

}
