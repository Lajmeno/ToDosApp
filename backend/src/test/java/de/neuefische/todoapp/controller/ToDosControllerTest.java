package de.neuefische.todoapp.controller;


import de.neuefische.todoapp.login.model.LoginData;
import de.neuefische.todoapp.login.model.UserDocument;
import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDosControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateNewTestUser(){
        ResponseEntity<UserDocument> createResponse = restTemplate.postForEntity("/users", new UserDocument("test@email.com", "test", "test", "USER", null), UserDocument.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);


        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/auth/login", new LoginData("test@email.com", "test"), String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotBlank();

        ResponseEntity<List> addToDoResponse = restTemplate.exchange("/todos", HttpMethod.POST, new HttpEntity<>(new ToDo("Rechnung"),createHeaders(loginResponse.getBody())), List.class);
        assertThat(addToDoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<List> getToDosResponse = restTemplate.exchange("/todos", HttpMethod.GET, new HttpEntity<>(createHeaders(loginResponse.getBody())), List.class);
        assertThat(getToDosResponse.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    HttpHeaders createHeaders(String token) {
        return new HttpHeaders() {{
            String authHeader = "Bearer " + token;
            set("Authorization", authHeader);
        }};
    }


}