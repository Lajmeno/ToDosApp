package de.neuefische.todoapp.service;


import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.repo.ToDosRepo;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;

@Service
public class ToDosService {
    private ToDosRepo toDosRepo;

    public ToDosService(ToDosRepo toDosRepo) {
        this.toDosRepo = toDosRepo;
    }

    public List<ToDo> getTodos() {
        toDosRepo.getTodos().sort(Comparator.comparing(item -> item.getStatus()));
        return toDosRepo.getTodos();
    }

    public void addToDo(ToDo toDo) {
        toDosRepo.addToDo(toDo);
    }

    public void changeToDoStatus(String id, ToDo todo) {
        toDosRepo.getTodos().stream().filter(ele -> ele.getId().equals(id))
                .findFirst()
                .ifPresent(ele ->ele.setStatus(todo.getStatus()));
    }

    public void removeToDo(String id) {
        toDosRepo.getTodos().stream().filter(ele -> ele.getId().equals(id))
                .findFirst()
                .ifPresent(ele ->toDosRepo.getTodos().remove(ele));
    }
}
