package com.example.homeforrent.websocket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRequestRepository extends MongoRepository<ChatRequest, String> {
    List<ChatRequest> findByTo(String to);
    List<ChatRequest> findByFrom(String from);
    Optional<ChatRequest> findByFromAndTo(String from, String to);
}

