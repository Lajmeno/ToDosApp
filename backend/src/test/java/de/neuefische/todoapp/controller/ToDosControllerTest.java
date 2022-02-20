package de.neuefische.todoapp.controller;


import de.neuefische.todoapp.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDosControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void expectToGetOneToDo(){
        ToDo toDo = new ToDo();
        toDo.setTitle("Rechnung Test");
        restTemplate.postForEntity("/todos/add",toDo, ToDo[].class );
        ResponseEntity<ToDo[]> response = restTemplate.getForEntity("/todos", ToDo[].class);
        assertEquals(toDo, response.getBody()[0]);
        //assertTrue(response.getBody().length > 0);
    }
    /*
    @Test
    void expectToChangeStatusOf1ToDo(){
        ToDo toDo = new ToDo();
        toDo.setTitle("Rechnung Test");
        restTemplate.postForEntity("/todos/add",toDo, ToDo[].class );
        restTemplate.put("/todos/updatestatus/{date}", toDo.getDateTime());
        ResponseEntity<ToDo[]> response = restTemplate.getForEntity("/todos", ToDo[].class);
        assertEquals(Status.INPROGRESS, response.getBody()[0].getStatus());
        //assertTrue(response.getBody().length > 0);
    }

     */



}