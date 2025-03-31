package com.example.DockerTest.Service;

import com.example.DockerTest.Entity.Response.TaskResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class TaskManagementService {

    private final WebClient taskManagementWebClient;
    private final TaskManagementAuthService taskManagementAuthService;

    public TaskManagementService(WebClient taskManagementWebClient, TaskManagementAuthService taskManagementAuthService) {
        this.taskManagementWebClient = taskManagementWebClient;
        this.taskManagementAuthService = taskManagementAuthService;
    }

    @Value("${task-management.getTasks-url}")
    private String retrieveTasksUrl;

    public List<TaskResponse> retrievetasks(){

        String token = taskManagementAuthService.retrieveToken();

        return taskManagementWebClient.get()
                .uri(retrieveTasksUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TaskResponse>>() {})
                .block();

    }

}
