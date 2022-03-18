package de.neuefische.todoapp.login.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
public class UserDocument {

    private String email;
    private String password;
    private String verifyPassword;
    private String role;

    @Id
    private String id;

}
