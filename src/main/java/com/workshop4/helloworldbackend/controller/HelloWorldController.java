package com.workshop4.helloworldbackend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HelloWorldController {

    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "hello world");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Java Spring Boot Backend API");
        response.put("version", "1.0.0");
        response.put("endpoints", Map.of(
            "hello", "/api/hello",
            "helloWithName", "/api/hello/{name}",
            "health", "/actuator/health"
        ));
        return ResponseEntity.ok(response);
    }
}