package com.workshop4.helloworldbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldBackendApplication.class, args);
        System.out.println("🚀 Hello World Backend is running!");
        System.out.println("📍 Access the API at: http://localhost:8080/api/hello");
        System.out.println("📍 API documentation at: http://localhost:8080/api/");
        System.out.println("🔍 Health check at: http://localhost:8080/actuator/health");
    }
}