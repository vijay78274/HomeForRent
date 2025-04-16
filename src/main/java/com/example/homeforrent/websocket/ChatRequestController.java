package com.example.homeforrent.websocket;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    @PostMapping("tenet/request-send")
    public ResponseEntity<?> sendRequest(@RequestParam String landlordUsername, Principal principal) {
        String tenetUsername=principal.getName();
        if (chatRequestRepo.findByTenantUsernameAndLandlordUsername(tenetUsername,landlordUsername).isPresent()) {
            return ResponseEntity.badRequest().body("Request already exists");
        }
        ChatRequest request = new ChatRequest();
        request.setTenantUsername(tenetUsername);
        request.setLandlordUsername(landlordUsername); 
        request.setStatus("PENDING");
        request.setTimestamp(LocalDateTime.now());
        chatRequestRepo.save(request);
        return ResponseEntity.ok("Request sent");
    }

    // Landlord accepts a request
    @PostMapping("landlord/request-accept")
    public ResponseEntity<?> acceptRequest(@RequestBody Map<String, String> body) {
        String tenant = body.get("tenantUsername");
        String landlord = body.get("landlordUsername");

        Optional<ChatRequest> optional = chatRequestRepo.findByTenantUsernameAndLandlordUsername(tenant, landlord);
        if (optional.isPresent()) {
            ChatRequest request = optional.get();
            request.setStatus("ACCEPTED");
            chatRequestRepo.save(request);
            return ResponseEntity.ok("Request accepted");
        }
        return ResponseEntity.badRequest().body("Request not found");
    }

    // Get requests for a landlord or tenant
    @GetMapping("/tenet/request")
    public ResponseEntity<List<ChatRequest>> getTenetRequests(@RequestParam String username) {
        List<ChatRequest> req = chatRequestRepo.findByTenantUsername(username);
        System.out.println(req);
        return ResponseEntity.ok(req);
    }
    @GetMapping("/landlord/request")
    public ResponseEntity<List<ChatRequest>> getLandlordRequests(@RequestParam String username) {
        List<ChatRequest> req = chatRequestRepo.findByLandlordUsername(username);
        System.out.println(req);
        return ResponseEntity.ok(req);
    }

    @GetMapping("/tenet/chat-page")
public String tenetChatSection(Model model, Authentication authentication) {
    MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
    String tenantUsername = userDetails.getUsername();
    // Tenet tenet = tenetRepository.findByUserName(tenantUsername);
    // String image = tenet.getImage();
    List<ChatRequest> requests = chatRequestRepo.findByTenantUsername(tenantUsername);
    model.addAttribute("acceptedRequests", requests.stream().filter(r -> "ACCEPTED".equals(r.getStatus())).toList());
    model.addAttribute("pendingRequests", requests.stream().filter(r -> "PENDING".equals(r.getStatus())).toList());
    return "ChatSection";
}
@GetMapping("/landlord/chat-page")
public String landlordChatSection(Model model,Authentication authentication) {
    MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
    String landlordUsername = userDetails.getUsername();
    Landlord landlord = repository.findByuserName(landlordUsername);
    String image = landlord.getImages();
    List<ChatRequest> requests = chatRequestRepo.findByLandlordUsername(landlordUsername);
    model.addAttribute("acceptedRequests", requests.stream().filter(r -> "ACCEPTED".equals(r.getStatus())).toList());
    model.addAttribute("pendingRequests", requests.stream().filter(r -> "PENDING".equals(r.getStatus())).toList());
    model.addAttribute("image",image);
    return "ChatSectionLandlord";
}

}
