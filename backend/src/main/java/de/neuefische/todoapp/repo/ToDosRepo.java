package de.neuefische.todoapp.repo;

import de.neuefische.todoapp.model.ToDo;
import org.springframework.stereotype.Repository;

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

}
