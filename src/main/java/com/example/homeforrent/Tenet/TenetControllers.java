package com.example.homeforrent.Tenet;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.homeforrent.LandLord.Landlord;
import com.example.homeforrent.User.MyUserDetails;
import com.example.homeforrent.contract.Contract;
import com.example.homeforrent.contract.ContractRepository;
import com.example.homeforrent.contract.FullContract;
import com.example.homeforrent.contract.FullContractRepository;
import com.example.homeforrent.websocket.ChatRequestRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/tenet")
public class TenetControllers { 
    @Autowired
    private FullContractRepository fullContractRepository;
    @Autowired
    private TenetService tenetService;
    @Autowired
    private ChatRequestRepository repository;
    @Autowired
    private ContractRepository contractRepository;
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
    @RequestParam String rawPassword) {
        tenetService.createTenet(name, age, gender, occupation, martial, TypeofRoom, RoomFor, imageUrl, userName, rawPassword);
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
    @GetMapping("/tenetcontract")
    public String tenetContract(Model model, Authentication authentication){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String to = userDetails.getUsername();
        List<Contract> contracts = contractRepository.findByTo(to);
        model.addAttribute("contract", contracts);
        return "Contracts";
    }
    @GetMapping("/getContract")
    public String getContract(@RequestParam String to, Authentication authentication, Model model) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String from = userDetails.getUsername();
        Contract contract = contractRepository.findByFromAndTo(to, from);
        System.out.print(contract.getFrom()+" "+contract.getTo());
        model.addAttribute("contract", contract);
        return "TenetContract";
    }
    
    @GetMapping("/getbyId/{userName}")
    public String getLandLordById(@PathVariable String userName, Model model){
        Landlord landlord = tenetService.getLandlordbyId(userName);
        // Optional<ChatRequest> request = repository.findByFromAndTo(userName,"");

        model.addAttribute("landlord", landlord);
        return "SingleTenet";
    }

    @GetMapping("/currentLandlord")
    public String getMethodName(Authentication authentication,Model model) {
        MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
        String tenant = userDetails.getUsername();
        FullContract fullContract =  fullContractRepository.findByTenantName(tenant);
        model.addAttribute("fullcontract",fullContract);
        return "CurrentLandlord";
    }
    
}
