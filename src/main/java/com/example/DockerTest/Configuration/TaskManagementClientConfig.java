package com.example.DockerTest.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TaskManagementClientConfig {

    @Value("${task-management.base-url}")
    private String taskManagementBaseUrl;

    @Bean(name = "taskManagementWebClient")
    public WebClient webClient(WebClient.Builder builder){
        return builder.baseUrl(taskManagementBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
