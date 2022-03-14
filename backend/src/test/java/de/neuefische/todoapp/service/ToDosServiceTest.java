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
        when(mockRepo.findAll()).thenReturn(List.of(toDo));

        assertEquals(List.of(toDo), service.getTodos());
    }

    @Test
    @Disabled
    void expectToChangeStatusToInprogress(){
        ToDosRepo mockRepo = mock(ToDosRepo.class);
        ToDosService service = new ToDosService(mockRepo);
        ToDo toDo = new ToDo("Stromrechnung");
        service.addToDo(toDo);

        ToDo toDo2 = new ToDo("Stromrechnung");
        toDo2.setStatus(Status.INPROGRESS);
        service.addToDo(toDo);

        when(mockRepo.findAll()).thenReturn(List.of(toDo, toDo2));
        service.changeToDoStatus(toDo.getId(), toDo2);

        assertTrue(service.getTodos().get(0).getStatus().equals(Status.INPROGRESS));
    }

    @Test
    void expectToRemove1ToDo(){
        ToDosRepo mockRepo = mock(ToDosRepo.class);
        ToDosService service = new ToDosService(mockRepo);
        ToDo toDo1 = new ToDo("Stromrechnung");
        ToDo toDo2 = new ToDo("Einkauf");
        service.addToDo(toDo1);
        service.addToDo(toDo2);
        when(mockRepo.findAll()).thenReturn(List.of(toDo1, toDo2));
        service.removeToDo(toDo1.getId());
        when(mockRepo.findAll()).thenReturn(List.of(toDo2));
        assertEquals(List.of(toDo2), service.getTodos());
    }

    @Test
    @Disabled
    void expectsToRemoveAllDoneToDos(){
        ToDosRepo mockRepo = mock(ToDosRepo.class);
        ToDosService service = new ToDosService(mockRepo);
        ToDo toDo1 = new ToDo("Stromrechnung");
        ToDo toDo2 = new ToDo("Einkauf");
        ToDo toDo3 = new ToDo("Never Done");
        toDo1.setStatus(Status.DONE);
        toDo2.setStatus(Status.DONE);
        service.addToDo(toDo1);
        service.addToDo(toDo2);
        service.addToDo(toDo3);

        service.removeDoneToDos();

        assertEquals(List.of(toDo3), service.getTodos());
    }

}