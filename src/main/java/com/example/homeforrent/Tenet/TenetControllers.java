package com.example.homeforrent.Tenet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.homeforrent.LandLord.Landlord;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/tenet")
public class TenetControllers { 
    @Autowired
    private TenetService tenetService;
    
    @PostMapping("/signup")
    public String postMethodName(@RequestParam String name,
    @RequestParam String age,
    @RequestParam String gender,
    @RequestParam String occupation,
    @RequestParam String martial,
    @RequestParam String TypeofRoom,
    @RequestParam String RoomFor,
    @RequestParam String imageUrl,
    @RequestParam String userName,
    @RequestParam String rawPassword, @RequestParam String address,
    @RequestParam double latitude,
    @RequestParam double longitude) {
        tenetService.createTenet(name, age, gender, occupation, martial, TypeofRoom, RoomFor, imageUrl, userName, rawPassword,address,latitude,longitude);
        return "redirect:/tenet/home";
    }
    @GetMapping("/home")
    public String MainScreen(Model model) {
        List<Landlord> landlords = tenetService.getAll();
        System.out.println(landlords);
        model.addAttribute("landlords",landlords); 
        return "TenetHome";
    }
    @GetMapping("/signup")
    public String signup() {
        return "TenetSignup";
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Landlord> getItems() {
        return tenetService.getAll();
    }
    @GetMapping("/getbyId/{userName}")
    public String getLandLordById(@PathVariable String userName, Model model){
        Landlord landlord = tenetService.getLandlordbyId(userName);
        model.addAttribute("landlord", landlord);
        return "SingleTenet";
    }
    // @GetMapping("/getbyId/{userName}")
    // public ResponseEntity<Landlord> getLandLordById(@PathVariable String userName, Model model){
    //     Landlord landlord = tenetService.getLandlordbyId(userName);
    //     return ResponseEntity.ok(landlord);
    // }
}
