package de.neuefische.todoapp.service;


import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.repo.ToDosRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDosService {

    private final ToDosRepo toDosRepo;


    public List<ToDo> getTodos(String email) {
        toDosRepo.findAllByCreatedByOrderByStatus(email);
        return toDosRepo.findAllByCreatedBy(email);
    }

    public void addToDo(ToDo toDo) {
        toDosRepo.save(toDo);
    }

    public void changeToDoStatus(String id, ToDo toDo, String email) {
        toDosRepo.findByIdAndCreatedBy(id, email)
                .map(t ->t.patch(toDo))
                .map(t ->toDosRepo.save(t));
    }

    public void removeToDo(String id, String email) {
        toDosRepo.findAllByCreatedBy(email).stream().filter(ele -> ele.getId().equals(id))
                .findFirst()
                .ifPresent(ele ->toDosRepo.delete(ele));
    }

    public void removeDoneToDos(String email) {
        var list = toDosRepo.findAllByCreatedBy(email).stream().filter(ele -> ele.getStatus().equals(Status.DONE))
                .toList();
        for (ToDo toDo: list) {
            toDosRepo.delete(toDo);
        }
    }

    public Optional<ToDo> getOneToDo(String id, String email) {
        return toDosRepo.findAllByCreatedBy(email).stream().filter(ele -> ele.getId().equals(id))
                .findFirst();
    }
}
