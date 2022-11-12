package ru.vdsimako.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:11-alpine:///practice",
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
        }
)
class UserControllerTest {

    @Test
    void createUser() {
    }
}