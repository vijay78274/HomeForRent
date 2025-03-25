package com.example.homeforrent.User;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String>{
    User findByusername(String userName);
    boolean existsByusername(String userName);
}
