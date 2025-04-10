package com.example.homeforrent.LandLord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.homeforrent.User.User;
import com.example.homeforrent.User.UserRepository;

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
    String[] TypeofRoom,
    String[] RoomFor,
    String userName,
    String rawPassword,
    String imageUrl,
    String address){
        if (userRepository.existsByusername(userName)) {
            return "Username already taken! Please choose a different one.";
        }
        Landlord landlord = new Landlord();
        User user = new User();
        landlord.setUserName(userName);
        landlord.setName(name);
        landlord.setRoomFor(RoomFor);
        landlord.setTypeofRoom(TypeofRoom);
        landlord.setImages(imageUrl);
        landlord.setStatus("Available");
        String password = passwordEncoder.encode(rawPassword);
        landlord.setPassword(password);
        landlord.setAddress(address);
        user.setUsername(userName);
        user.setPassword(password);
        user.setRole("Landlord");
        landLordRepository.save(landlord);
        userRepository.save(user);
        return "Account created successfully!";
    }
}
