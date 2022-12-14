package ru.vdsimako.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:11-alpine:///practice",
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
        }
)
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

}
