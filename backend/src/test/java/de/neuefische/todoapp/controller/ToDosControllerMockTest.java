package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.service.ToDosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToDosControllerMockTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ToDosService toDosService;


    @Test
    void expectToGetOneToDo(){
        ToDo toDo = new ToDo();
        toDo.setTitle("Rechnung Test");

        when(toDosService.getTodos()).thenReturn(List.of(toDo));

        ResponseEntity<ToDo[]> response = restTemplate.getForEntity("/todos", ToDo[].class);
        assertEquals(toDo, response.getBody()[0]);
        //assertTrue(response.getBody().length > 0);
    }
}
