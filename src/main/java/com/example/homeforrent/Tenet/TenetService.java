package com.example.homeforrent.Tenet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.homeforrent.LandLord.LandLordRepository;
import com.example.homeforrent.LandLord.Landlord;
import com.example.homeforrent.User.User;
import com.example.homeforrent.User.UserRepository;

@Service
public class TenetService {
    @Autowired
    LandLordRepository landLordRepository;
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
    String imageUrl,
    String userName,
    String rawPassword){
        if (userRepository.existsByusername(userName)) {
            return "Username already taken! Please choose a different one.";
        }
        Tenet tenet = new Tenet();
        User user = new User();
        tenet.setName(name);
        tenet.setAge(age);
        tenet.setGender(gender);
        tenet.setImage(imageUrl);
        tenet.setUserName(userName);
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
    public List<Landlord> getAll(){
        List<Landlord> list = landLordRepository.findAll();
        return list;
    }
    public Landlord getLandlordbyId(String id){
        Landlord landlord = landLordRepository.findByuserName(id);
        System.out.println(landlord.getUserName());
        return landlord;
    }
}
