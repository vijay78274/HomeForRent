package com.example.homeforrent.websocket;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.homeforrent.User.MyUserDetails;

@Controller
public class ChatMessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository messageRepo;

    @MessageMapping("/send")
    public void sendMessage(ChatMessage message) {
        message.setTimestamp(new Date());
        messageRepo.save(message); // Store in DB

        // Real-time delivery to the receiver
        messagingTemplate.convertAndSend("/topic/messages/" + message.getReceiver(), message);
    }

    @GetMapping("/chat/{receiverUsername}")
    public String chatPage(@PathVariable String receiverUsername, Model model, Authentication authenticate) {
        MyUserDetails myUserDetails = (MyUserDetails)authenticate.getPrincipal();

        String senderUsername = myUserDetails.getUsername();
        String role = myUserDetails.getRole();

        model.addAttribute("sender", senderUsername);
        model.addAttribute("receiver", receiverUsername);
        model.addAttribute("role", role);
        return "Chat";
    }
    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(@RequestParam String sender, @RequestParam String receiver) {
    try {
        List<ChatMessage> messages1 = messageRepo.findBySenderAndReceiver(sender, receiver);
        List<ChatMessage> messages2 = messageRepo.findByReceiverAndSender(sender, receiver);
        System.out.println("sender message: "+messages1.size()+" reciever message: "+messages2.size());
         List<ChatMessage> allMessages = new ArrayList<>();
    allMessages.addAll(messages1);
    allMessages.addAll(messages2);
    // Sort by timestamp
    allMessages.sort(Comparator.comparing(ChatMessage::getTimestamp));
        return ResponseEntity.ok(allMessages);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}
}

