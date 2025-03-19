package com.example.homeforrent;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LandLordRepository extends MongoRepository<Landlord,String>{
    Landlord findByUserName(String userName);
    boolean existsByUserName(String userName);
}
