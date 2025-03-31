package com.example.DockerTest.Controller;


import com.example.DockerTest.Entity.Response.TaskResponse;
import com.example.DockerTest.Entity.TestDocker;
import com.example.DockerTest.Repository.DockerTestRepository;
import com.example.DockerTest.Service.TaskManagementService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dockerTest/api/v1")
public class DockerTestController {

    private final DockerTestRepository dockerTestRepository;
    private final TaskManagementService taskManagementService;

    public DockerTestController(DockerTestRepository dockerTestRepository, TaskManagementService taskManagementService) {
        this.dockerTestRepository = dockerTestRepository;
        this.taskManagementService = taskManagementService;
    }

    @GetMapping("/getResponse")
    public ResponseEntity<Object> getResponse(){
		System.out.println("Namma dhan !");
        return new ResponseEntity<>("Test Success !", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Object> getValue(){
        System.out.println("Returning All Values");
        List<TestDocker> valueList = dockerTestRepository.findAll();
        return new ResponseEntity<>(valueList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createValue(@RequestBody TestDocker testDocker){
        TestDocker savedValue = dockerTestRepository.save(testDocker);
        System.out.println(savedValue.toString());
        return new ResponseEntity<>(savedValue,HttpStatus.CREATED);
    }

    @GetMapping("/getTasks")
    public ResponseEntity<Object> retrieveTasks(){
        List<TaskResponse> taskList = taskManagementService.retrievetasks();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

}
