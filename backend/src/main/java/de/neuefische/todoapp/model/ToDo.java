package de.neuefische.todoapp.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Document("toDos")
@Data
public class ToDo {

    private String id;
    private String dateTime;
    private String title;
    private String description;
    private Status status;
    private String createdBy;

    public ToDo(){
        this("");
    }

    public ToDo(String title){
        this.id = UUID.randomUUID().toString();
        this.status = Status.WAITING;
        this.title = title;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = now.format(formatter);
    }

    public ToDo patch(ToDo toDo) {
        if(toDo.getStatus() != null){
            setStatus(toDo.getStatus());
        }
        if (toDo.getTitle() != null){
            setTitle(toDo.getTitle());
        }
        if (toDo.getDescription() != null){
            setDescription(toDo.getDescription());
        }
        return this;
    }
}
