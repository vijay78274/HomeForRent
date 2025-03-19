package com.example.homeforrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LandLordService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LandLordRepository landLordRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Transactional
    public String createLandLord(
    String name,
    String phone,
    String TypeofRoom,
    String RoomFor,
    String userName,
    String rawPassword){
        if (userRepository.existsByusername(userName)) {
            return "Username already taken! Please choose a different one.";
        }
        Landlord landlord = new Landlord();
        User user = new User();
        landlord.setUserName(userName);
        // tenet.setRoom_images(image);
        landlord.setName(name);
        landlord.setPhone(phone);
        landlord.setRoomFor(RoomFor);
        landlord.setTypeofRoom(TypeofRoom);
        String password = passwordEncoder.encode(rawPassword);
        landlord.setPassword(password);
        user.setUsername(userName);
        user.setPassword(password);
        user.setRole("Landlord");
        landLordRepository.save(landlord);
        userRepository.save(user);
        return "Account created successfully!";
    }
}
