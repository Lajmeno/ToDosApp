package de.neuefische.todoapp.login.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDocument {

    private String email;
    private String password;
    private String verifyPassword;
    private String role;

    @Id
    private String id;

}
