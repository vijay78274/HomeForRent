package com.example.homeforrent.contract;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FullContractRepository extends MongoRepository<FullContract, String>{
    FullContract findByLandlordNameAndTenantName(String landlord, String tenant);
    FullContract findByTenantName(String tenant);
}
