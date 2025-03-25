package com.example.homeforrent.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController{
    @GetMapping("/login")
    public String login() {
        return "Login"; 
    }
    @GetMapping("/start")
    public String start(){
        return "Main";
    }
}