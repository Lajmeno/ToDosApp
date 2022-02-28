package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.service.ToDosService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin
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
    public List<ToDo> addToDo(@RequestBody ToDo toDo){
        toDosService.addToDo(toDo);
        return toDosService.getTodos();
    }

    @PutMapping("/{id}")
    public List<ToDo> changeStatus(@PathVariable String id, @RequestBody ToDo todo){
        toDosService.changeToDoStatus(id, todo);
        return toDosService.getTodos();
    }

    @DeleteMapping("/{id}")
    public List<ToDo> removeToDo(@PathVariable String id){
        toDosService.removeToDo(id);
        return toDosService.getTodos();
    }

    @DeleteMapping()
    public List<ToDo> removeAllDoneToDos(){
        toDosService.removeDoneToDos();
        return toDosService.getTodos();
    }


}
