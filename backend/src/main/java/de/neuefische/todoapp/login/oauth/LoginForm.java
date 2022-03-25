package de.neuefische.todoapp.login.oauth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    private String grant_type;
    private String code;
    private String redirect_uri;



}
