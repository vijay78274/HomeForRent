package com.example.homeforrent.LandLord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.homeforrent.Tenet.Tenet;

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
    public String MainScreen(Model model){
        List<Tenet> tenants = landLordService.getAll();
        System.out.println(tenants);
        model.addAttribute("tenant",tenants); 
        return "LandlordHome";
    }
    @GetMapping("/signup")
    public String signup() {
        return "LandlordSignup";
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Tenet> getItems() {
        return landLordService.getAll();
    }
}