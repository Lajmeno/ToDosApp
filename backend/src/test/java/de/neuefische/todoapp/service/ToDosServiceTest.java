package de.neuefische.todoapp.service;

import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.repo.ToDosRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToDosServiceTest {

    @Test
    void shouldAdd1ToDo(){
        ToDosRepo mockRepo = mock(ToDosRepo.class);
        ToDosService service = new ToDosService(mockRepo);
        ToDo toDo = new ToDo("Aufraeumen");
        service.addToDo(toDo);
        when(mockRepo.findAllByCreatedBy("test@mail.com")).thenReturn(List.of(toDo));

        assertEquals(List.of(toDo), service.getTodos("test@mail.com"));
    }

    @Test
    void expectToChangeStatusToInprogress(){
        ToDosRepo mockRepo = mock(ToDosRepo.class);
        ToDosService service = new ToDosService(mockRepo);
        ToDo toDo = new ToDo("Stromrechnung");
        service.addToDo(toDo);

        ToDo toDo2 = new ToDo("Stromrechnung");
        toDo2.setStatus(Status.INPROGRESS);
        service.addToDo(toDo);

        when(mockRepo.findAllByCreatedBy("test@mail.com")).thenReturn(List.of(toDo, toDo2));
        service.changeToDoStatus(toDo.getId(), toDo2, "test@mail.com");

        assertTrue(service.getTodos("test@mail.com").get(1).getStatus().equals(Status.INPROGRESS));
    }

    @Test
    void expectToRemove1ToDo(){
        ToDosRepo mockRepo = mock(ToDosRepo.class);
        ToDosService service = new ToDosService(mockRepo);
        ToDo toDo1 = new ToDo("Stromrechnung");
        ToDo toDo2 = new ToDo("Einkauf");
        service.addToDo(toDo1);
        service.addToDo(toDo2);
        when(mockRepo.findAllByCreatedBy("test@mail.com")).thenReturn(List.of(toDo1, toDo2));
        service.removeToDo(toDo1.getId(), "test@mail.com");
        when(mockRepo.findAllByCreatedBy("test@mail.com")).thenReturn(List.of(toDo2));
        assertEquals(List.of(toDo2), service.getTodos("test@mail.com"));
    }


}