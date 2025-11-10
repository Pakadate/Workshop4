package com.workshop4.helloworldbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class HelloWorldBackendApplicationTests {

    @Test
    void contextLoads() {
        // Test that the Spring Boot context loads successfully
    }
}