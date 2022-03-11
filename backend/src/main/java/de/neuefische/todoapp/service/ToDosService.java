package de.neuefische.todoapp.service;


import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.repo.ToDosRepo;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ToDosService {
    private ToDosRepo toDosRepo;

    public ToDosService(ToDosRepo toDosRepo) {
        this.toDosRepo = toDosRepo;
    }

    public List<ToDo> getTodos() {
        toDosRepo.findAllByOrderBOrderByStatus();
        return toDosRepo.findAll();
    }

    public void addToDo(ToDo toDo) {
        toDosRepo.save(toDo);
    }

    public void changeToDoStatus(String id, ToDo todo) {
        toDosRepo.findAll().stream().filter(ele -> ele.getId().equals(id))
                .findFirst()
                .ifPresent(ele ->ele.setStatus(todo.getStatus()));
    }

    public void removeToDo(String id) {
        toDosRepo.findAll().stream().filter(ele -> ele.getId().equals(id))
                .findFirst()
                .ifPresent(ele ->toDosRepo.delete(ele));
    }

    public void removeDoneToDos() {
        var list = toDosRepo.findAll().stream().filter(ele -> ele.getStatus().equals(Status.DONE))
                .toList();
        for (ToDo toDo: list) {
            toDosRepo.findAll().remove(toDo);
        }
    }

    public Optional<ToDo> getOneToDO(String id) {
        return toDosRepo.findAll().stream().filter(ele -> ele.getId().equals(id))
                .findFirst();
    }
}
