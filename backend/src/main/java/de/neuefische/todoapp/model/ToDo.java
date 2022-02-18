package de.neuefische.todoapp.model;

import java.util.Objects;

public class ToDo {

    private String id;
    private String title;
    private String content;

    private Status status;

    public ToDo(){
        this.status = Status.WAITING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return status == toDo.status && Objects.equals(id, toDo.id) && Objects.equals(title, toDo.title) && Objects.equals(content, toDo.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status);
    }
}
