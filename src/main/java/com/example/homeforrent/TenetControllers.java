package com.example.homeforrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/tenet")
public class TenetControllers {
    @Autowired
    private TenetService tenetService;
    
    @PostMapping("/signup")
    public String postMethodName(@RequestParam String name,
    @RequestParam String age,
    @RequestParam String gender,
    @RequestParam String occuption,
    @RequestParam String martial,
    @RequestParam String TypeofRoom,
    @RequestParam String RoomFor,
    @RequestParam String image,
    @RequestParam String userName,
    @RequestParam String rawPassword) {
        tenetService.createTenet(name, age, gender, occuption, martial, TypeofRoom, RoomFor, image, userName, rawPassword);
        return "redirect:/tenet/home";
    }
    @GetMapping("/home")
    public String MainScreen() {
        return "TenetHome";
    }
    @GetMapping("/signup")
    public String signup() {
        return "TenetSignup";
    }
    
}
