package de.neuefische.todoapp.service;

import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.repo.ToDosRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDosServiceTest {

    @Test
    void shouldAdd1ToDo(){
        ToDosRepo repo = new ToDosRepo();
        ToDosService service = new ToDosService(repo);
        ToDo toDo = new ToDo("Aufraeumen");
        service.addToDo(toDo);

        assertEquals(List.of(toDo), service.getTodos());
    }

    @Test
    void expectToChangeStatusToInprogress(){
        ToDosRepo repo = new ToDosRepo();
        ToDo toDo = new ToDo("Stromrechnung");
        repo.addToDo(toDo);

        ToDo toDo2 = new ToDo("Stromrechnung");
        toDo2.setStatus(Status.INPROGRESS);
        repo.addToDo(toDo);

        ToDosService service = new ToDosService(repo);

        service.changeToDoStatus(toDo.getId(), toDo2);

        assertTrue(repo.getTodos().get(0).getStatus().equals(Status.INPROGRESS));
    }

    @Test
    void expectToRemove1ToDo(){
        ToDosRepo repo = new ToDosRepo();
        ToDo toDo1 = new ToDo("Stromrechnung");
        ToDo toDo2 = new ToDo("Einkauf");
        repo.addToDo(toDo1);
        repo.addToDo(toDo2);

        ToDosService service = new ToDosService(repo);
        service.removeToDo(toDo1.getId());

        assertEquals(List.of(toDo2), service.getTodos());
    }

}