package com.example.homeforrent.Tenet;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TenetRepository extends MongoRepository<Tenet,String>{
    Tenet findByUserName(String userName);
    boolean existsByUserName(String userName);
}
