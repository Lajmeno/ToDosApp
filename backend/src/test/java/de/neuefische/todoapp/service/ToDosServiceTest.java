package de.neuefische.todoapp.service;

import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.repo.ToDosRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDosServiceTest {

    @Test
    void expectToChangeStatusToInprogress(){
        ToDosRepo repo = new ToDosRepo();
        ToDo toDo = new ToDo("Stromrechnung");
        repo.addToDo(toDo);

        ToDosService service = new ToDosService(repo);

        service.changeToDoStatus(toDo.getDateTime(), "INPROGRESS");
        System.out.println(repo.getTodos().get(0).getStatus());

        assertTrue(repo.getTodos().get(0).getStatus().equals(Status.INPROGRESS));

    }

}