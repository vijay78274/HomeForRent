package com.example.homeforrent.LandLord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/landlord")
public class LandLordController {
    @Autowired
    private LandLordService landLordService;
    
    @PostMapping("/signup")
    public String postMethodName(@RequestParam String name,
    @RequestParam String[] TypeofRoom,
    @RequestParam String[] RoomFor,
    @RequestParam String userName,
    @RequestParam String rawPassword,
    @RequestParam String imageUrl, @RequestParam String address) {
        landLordService.createLandLord(name, TypeofRoom, RoomFor, userName, rawPassword, imageUrl,address);
        return "redirect:/landlord/home";
    }
    @GetMapping("/home")
    public String MainScreen() {
        return "LandlordHome";
    }
    @GetMapping("/signup")
    public String signup() {
        return "LandlordSignup";
    }
}