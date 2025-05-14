package com.example.homeforrent.contract;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.homeforrent.User.MyUserDetails;


import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ContractController {
    @Autowired
    private ContractRepository repository;
    @Autowired 
    private FullContractRepository fullContractRepository;
    
    @PostMapping("/contract/save")
    public String postMethodName(@RequestParam String to,
    @RequestParam String fullName,
    @RequestParam String adhaar,
    @RequestParam String roomFor,
    @RequestParam String roomType,
    @RequestParam String rent,
    @RequestParam String phone,
    @RequestParam String adhaarImage,
    Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        String from = userDetails.getUsername();
        String role = userDetails.getRole();
        Contract contract = new Contract();
        contract.setFrom(from);
        contract.setTo(to);
        contract.setAdhaar(adhaar);
        contract.setContractDate(LocalDateTime.now());
        contract.setPhone(phone);
        contract.setRole(role);
        contract.setRent(rent);
        contract.setFullName(fullName);
        contract.setRoomFor(roomFor);
        contract.setRoomType(roomType);
        contract.setAdhaarImage(adhaarImage);
        repository.save(contract);
        if(repository.findByFromAndTo(to, from)!=null){
            Contract landContract = repository.findByFromAndTo(to, from);
            FullContract fullContract = new FullContract(to, from, LocalDateTime.now(), adhaar, landContract.getAdhaar(), roomFor, roomType, rent, landContract.getPhone(), phone);
            fullContractRepository.save(fullContract);
        }
        return "redirect:/chat-page";
    }
    @GetMapping("/landlordcontract")
    public String contract(@RequestParam String to, Model model){
        model.addAttribute("to",to);
        return "LandlordContract";
    }
    
    @GetMapping("fullcontract/get")
    public String fullContract(@RequestParam String from, Authentication authentication, Model model) {
        MyUserDetails details = (MyUserDetails)authentication.getPrincipal();
        String to = details.getUsername();
        FullContract fullContract = fullContractRepository.findByLandlordNameAndTenantName(from, to);
        model.addAttribute("fullcontract", fullContract);
        return "FullContract";
    }
    
}
