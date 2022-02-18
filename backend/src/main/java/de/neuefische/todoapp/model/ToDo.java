package de.neuefische.todoapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ToDo {

    private LocalDateTime dateTime;
    private String title;
    private String content;

    private Status status;

    public ToDo(){
        this.status = Status.WAITING;
        //Instant now = Instant.now();
        this.dateTime = LocalDateTime.now();
    }

    public ToDo(String title){
        this.status = Status.WAITING;
//        Instant now = Instant.now();
        this.dateTime = LocalDateTime.now();
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(dateTime, toDo.dateTime) && Objects.equals(title, toDo.title) && Objects.equals(content, toDo.content) && status == toDo.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, title, content, status);
    }
}
