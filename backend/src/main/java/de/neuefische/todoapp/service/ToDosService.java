package de.neuefische.todoapp.service;

import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.repo.ToDosRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ToDosService {
    private ToDosRepo toDosRepo;

    public ToDosService(ToDosRepo toDosRepo) {
        this.toDosRepo = toDosRepo;
    }

    public List<ToDo> getTodos() {
        return toDosRepo.getTodos();
    }

    public void addToDo(ToDo toDo) {
        toDosRepo.addToDo(toDo);
    }

    public void changeToDoStatus(LocalDateTime date, String status) {
        toDosRepo.changeToDoStatus(date, status);
    }
}
