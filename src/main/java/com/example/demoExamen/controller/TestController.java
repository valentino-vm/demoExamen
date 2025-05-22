package com.example.demoExamen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoExamen.entity.TestEntity;
import com.example.demoExamen.repository.TestRepository;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping
    public String testConnection() {
        try {
            // Try to get all entities to test connection
            List<TestEntity> entities = testRepository.findAll();
            return "Database connection successful! Found " + entities.size() + " entities.";
        } catch (Exception e) {
            return "Connection error: " + e.getMessage();
        }
    }

    @PostMapping("/create")
    public String createTestEntity() {
        try {
            TestEntity entity = new TestEntity();
            entity.setName("Test Entity " + System.currentTimeMillis());
            testRepository.save(entity);
            return "Entity created successfully!";
        } catch (Exception e) {
            return "Error creating entity: " + e.getMessage();
        }
    }
}
