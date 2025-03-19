package com.example.homeforrent;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TenetRepository extends MongoRepository<Tenet,String>{
    Tenet findByUserName(String userName);
    boolean existsByUserName(String userName);
}
