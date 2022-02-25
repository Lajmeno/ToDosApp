package de.neuefische.todoapp.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ToDo {

    private String id;
    private LocalDateTime dateTime;
    private String title;
    private String content;

    private Status status;

    public ToDo(){
        this("");
    }

    public ToDo(String title){
        this.id = UUID.randomUUID().toString();
        this.status = Status.WAITING;
        this.dateTime = LocalDateTime.now();
        this.title = title;
    }
}
