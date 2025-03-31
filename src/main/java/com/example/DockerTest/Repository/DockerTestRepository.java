package com.example.DockerTest.Repository;

import com.example.DockerTest.Entity.TestDocker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DockerTestRepository extends JpaRepository<TestDocker, Long>{
}
