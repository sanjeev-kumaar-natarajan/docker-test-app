package com.example.DockerTest.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "test_docker_schema", name = "test_docker")
public class TestDocker {

    @Override
    public String toString() {
        return "TestDocker{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

}
