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

    @PostMapping("/add")
    public void addToDo(@RequestBody ToDo toDo){
        toDosService.addToDo(toDo);
    }


    @PutMapping("/updatestatus/{date}")
    public void changeStatus(@PathVariable String date, @RequestBody String[] statusArray){
        String status = statusArray[0];
        LocalDateTime toDate = LocalDateTime.parse(date);
        toDosService.changeToDoStatus(toDate, status);
    }

    @PutMapping("/remove/{date}")
    public void removeToDo(@PathVariable String date){
        LocalDateTime toDate = LocalDateTime.parse(date);
        toDosService.removeToDo(toDate);
    }


}
