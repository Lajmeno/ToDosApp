package de.neuefische.todoapp.login.controller;


import de.neuefische.todoapp.login.model.UserDocument;
import de.neuefische.todoapp.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/me")
    public String findUserInRepo(Principal principal){
        return principal.toString();
    }

    @PostMapping
    public ResponseEntity<UserDocument> saveUserInRepo(@RequestBody UserDocument user){
        if(user.getPassword().equals(user.getVerifyPassword())){
            user.setRole("USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setVerifyPassword(passwordEncoder.encode(user.getVerifyPassword()));
            Optional<UserDocument> newUser= userService.saveUser(user);
            return ResponseEntity.of(newUser);
        }
        return ResponseEntity.notFound().build();

    }
}
