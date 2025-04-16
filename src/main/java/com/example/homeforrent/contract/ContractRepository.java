package com.example.homeforrent.contract;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContractRepository extends MongoRepository<Contract,String>{
    Contract findByFromAndTo(String from, String to);
}
