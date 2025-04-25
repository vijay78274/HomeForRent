package com.example.homeforrent.websocket;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.homeforrent.LandLord.LandLordRepository;
import com.example.homeforrent.LandLord.Landlord;
import com.example.homeforrent.User.MyUserDetails;


@Controller
// @RequestMapping("/chat-request")
public class ChatRequestController {

    @Autowired
    private LandLordRepository repository;

    @Autowired
    private ChatRequestRepository chatRequestRepo;
    // Tenant sends a request
    @PostMapping("/request-send")
    public ResponseEntity<?> sendRequest(@RequestParam String to, Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
        String from=userDetails.getUsername();
        if (chatRequestRepo.findByFromAndTo(from,to).isPresent() || chatRequestRepo.findByFromAndTo(to, from).isPresent()) {
            return ResponseEntity.badRequest().body("Request already exists! Go to chat section");
        }
        ChatRequest request = new ChatRequest();
        request.setFrom(from);
        request.setto(to); 
        request.setStatus("PENDING");
        request.setTimestamp(LocalDateTime.now());
        chatRequestRepo.save(request);
        return ResponseEntity.ok("Request sent");
    }

    // Landlord accepts a request
    @PostMapping("/request-accept")
    public ResponseEntity<?> acceptRequest(@RequestParam String from, Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
        String to = userDetails.getUsername();

        Optional<ChatRequest> optional = chatRequestRepo.findByFromAndTo(from, to);
        if (optional.isPresent()) {
            ChatRequest request = optional.get();
            request.setStatus("ACCEPTED");
            chatRequestRepo.save(request);
            return ResponseEntity.ok("Request accepted");
        }
        return ResponseEntity.badRequest().body("Request not found");
    }

    // Get requests for a landlord or tenant
    @GetMapping("/request")
    public ResponseEntity<List<ChatRequest>> getTenetRequests(Authentication authentication) {
        MyUserDetails user = (MyUserDetails)authentication.getPrincipal();
        String username = user.getUsername();
        List<ChatRequest> req = chatRequestRepo.findByTo(username);
        System.out.println(req);
        return ResponseEntity.ok(req);
    }

    @GetMapping("/chat-page")
public String tenetChatSection(Model model, Authentication authentication) {
    MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
    String tenantUsername = userDetails.getUsername();
    List<ChatRequest> requests1 = chatRequestRepo.findByTo(tenantUsername);
    List<ChatRequest> requests2 = chatRequestRepo.findByFrom(tenantUsername);
    List<ChatRequest> allRequests = new ArrayList<>();
    allRequests.addAll(requests1);
    allRequests.addAll(requests2);
    model.addAttribute("acceptedRequests", allRequests.stream().filter(r -> "ACCEPTED".equals(r.getStatus())).toList());
    model.addAttribute("pendingRequests", allRequests.stream().filter(r -> "PENDING".equals(r.getStatus())).toList());
    return "ChatSection";
}
// @GetMapping("/landlord/chat-page")
// public String landlordChatSection(Model model,Authentication authentication) {
//     MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
//     String landlordUsername = userDetails.getUsername();
//     Landlord landlord = repository.findByuserName(landlordUsername);
//     String image = landlord.getImages();
//     List<ChatRequest> requests = chatRequestRepo.findByLandlordUsername(landlordUsername);
//     model.addAttribute("acceptedRequests", requests.stream().filter(r -> "ACCEPTED".equals(r.getStatus())).toList());
//     model.addAttribute("pendingRequests", requests.stream().filter(r -> "PENDING".equals(r.getStatus())).toList());
//     model.addAttribute("image",image);
//     return "ChatSectionLandlord";
// }

}
