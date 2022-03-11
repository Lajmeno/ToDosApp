package de.neuefische.todoapp.controller;


import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDosControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void expectToGetOneToDo(){
        ToDo toDo = new ToDo();
        toDo.setTitle("Rechnung Test");
        restTemplate.postForEntity("/todos",toDo, ToDo[].class );
        ResponseEntity<ToDo[]> response = restTemplate.getForEntity("/todos", ToDo[].class);
        assertThat(response.getBody()).contains(toDo);
    }

    @Test
    @Disabled
    void expectToChangeStatusOf1ToDo(){
        ToDo toDo = new ToDo();
        toDo.setTitle("Einkauf");
        ToDo toDo2 = new ToDo("Pizza");
        toDo2.setStatus(Status.INPROGRESS);
        restTemplate.postForEntity("/todos", toDo, ToDo[].class );
        restTemplate.put("/todos/" + toDo.getId(), toDo2);
        ResponseEntity<ToDo[]> response = restTemplate.getForEntity("/todos", ToDo[].class);

        assertEquals(Status.INPROGRESS, response.getBody()[0].getStatus());
    }

}