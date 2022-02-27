package de.neuefische.todoapp.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
public class ToDo {

    private String id;
    private String dateTime;
    private String title;
    private String content;

    private Status status;

    public ToDo(){
        this("");
    }

    public ToDo(String title){
        this.id = UUID.randomUUID().toString();
        this.status = Status.WAITING;
        this.title = title;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        this.dateTime = now.format(formatter);
    }
}
