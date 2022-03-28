package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.model.ToDo;
import de.neuefische.todoapp.service.ToDosService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDosController {

    private final ToDosService toDosService;

    public ToDosController(ToDosService toDosService) {
        this.toDosService = toDosService;
    }

    @GetMapping
    public List<ToDo> getAllToDos(Principal principal){
        return toDosService.getTodos(principal.getName());
    }

    @GetMapping("/{id}")
    public Optional<ToDo> getToDoById(@PathVariable String id, Principal principal){
        return toDosService.getOneToDo(id, principal.getName());
    }

    @PostMapping()
    public List<ToDo> addToDo(@RequestBody ToDo toDo, Principal principal){
        toDo.setCreatedBy(principal.getName());
        toDosService.addToDo(toDo);
        return toDosService.getTodos(toDo.getCreatedBy());
    }

    @PatchMapping ("/{id}")
    public List<ToDo> changeToDo(@PathVariable String id, @RequestBody ToDo todo, Principal principal){
        toDosService.changeToDoStatus(id, todo, principal.getName());
        return toDosService.getTodos(principal.getName());
    }

    @DeleteMapping("/{id}")
    public List<ToDo> removeToDo(@PathVariable String id, Principal principal){
        toDosService.removeToDo(id, principal.getName());
        return toDosService.getTodos(principal.getName());
    }

    @DeleteMapping()
    public List<ToDo> removeAllDoneToDos(Principal principal){
        toDosService.removeDoneToDos(principal.getName());
        return toDosService.getTodos(principal.getName());
    }


}
