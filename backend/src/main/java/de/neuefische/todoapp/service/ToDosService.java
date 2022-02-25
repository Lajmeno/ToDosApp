package de.neuefische.todoapp.service;


import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.repo.ToDosRepo;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
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

    public void changeToDoStatus(LocalDateTime date, ToDo todo) {
        toDosRepo.getTodos().stream().filter(ele -> ele.getDateTime().equals(date))
                .findFirst()
                .ifPresent(ele ->ele.setStatus(todo.getStatus()));
    }

    public void removeToDo(LocalDateTime date) {
        toDosRepo.getTodos().stream().filter(ele -> ele.getDateTime().equals(date))
                .findFirst()
                .ifPresent(ele ->toDosRepo.getTodos().remove(ele));
    }
}
