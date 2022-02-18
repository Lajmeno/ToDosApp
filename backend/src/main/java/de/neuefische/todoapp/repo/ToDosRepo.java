package de.neuefische.todoapp.repo;

import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.ToDo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDosRepo {

    private List<ToDo> todos = new ArrayList<>();

    public List<ToDo> getTodos() {
        return todos;
    }

    public void addToDo(ToDo toDo) {
        todos.add(toDo);
    }

    public void changeToDoStatus(LocalDateTime date, String status) {
        Status toStatus = Status.valueOf(status);
        todos.stream().filter(ele -> ele.getDateTime().equals(date)).
                findFirst().
                ifPresent(ele ->ele.setStatus(toStatus));
    }
}
