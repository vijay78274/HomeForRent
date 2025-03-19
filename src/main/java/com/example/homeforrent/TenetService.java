package com.example.homeforrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenetService {
    @Autowired 
    TenetRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public String createTenet(
    String name,
    String age,
    String gender,
    String occuption,
    String martial,
    String TypeofRoom,
    String RoomFor,
    String image,
    String userName,
    String rawPassword){
        if (userRepository.existsByusername(userName)) {
            return "Username already taken! Please choose a different one.";
        }
        Tenet tenet = new Tenet();
        User user = new User();
        tenet.setUserName(userName);
        tenet.setAge(age);
        tenet.setGender(gender);
        tenet.setImage(image);
        tenet.setName(name);
        tenet.setOccuption(occuption);
        tenet.setRoomFor(RoomFor);
        tenet.setTypeofRoom(TypeofRoom);
        String password = passwordEncoder.encode(rawPassword);
        tenet.setPassword(password);
        user.setUsername(userName);
        user.setPassword(password);
        user.setRole("Tenet");
        repository.save(tenet);
        userRepository.save(user);
        return "Account created successfully!";
    }
}
