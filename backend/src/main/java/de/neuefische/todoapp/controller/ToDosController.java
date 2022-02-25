package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.service.ToDosService;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDosController {

    private ToDosService toDosService;

    public ToDosController(ToDosService toDosService) {
        this.toDosService = toDosService;
    }

    @GetMapping
    public List<ToDo> getAllToDos(){
        return toDosService.getTodos();
    }

    @PostMapping()
    public void addToDo(@RequestBody ToDo toDo){
        toDosService.addToDo(toDo);
    }


    @PutMapping("/{id}")
    public void changeStatus(@PathVariable String id, @RequestBody ToDo todo){
        toDosService.changeToDoStatus(id, todo);
    }

    @DeleteMapping("/{id}")
    public void removeToDo(@PathVariable String id){
        toDosService.removeToDo(id);
    }


}
