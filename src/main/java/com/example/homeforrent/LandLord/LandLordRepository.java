package com.example.homeforrent.LandLord;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LandLordRepository extends MongoRepository<Landlord,String>{
    Landlord findByuserName(String userName);
    boolean existsByUserName(String userName);
}
