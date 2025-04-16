package com.example.homeforrent.websocket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRequestRepository extends MongoRepository<ChatRequest, String> {
    List<ChatRequest> findByLandlordUsername(String landlordUsername);
    List<ChatRequest> findByTenantUsername(String tenantUsername);
    Optional<ChatRequest> findByTenantUsernameAndLandlordUsername(String tenant, String landlord);
}

