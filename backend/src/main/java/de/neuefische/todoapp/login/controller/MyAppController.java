package de.neuefische.todoapp.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/todos/admin")
public class MyAppController {

    @GetMapping
    public String sayHello(Principal principal){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hi there "+ auth.getAuthorities()+ " " + principal.getName() +"!";
    }

}
