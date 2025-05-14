package com.example.homeforrent.contract;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface ContractRepository extends MongoRepository<Contract,String>{
    Contract findByFromAndTo(String from, String to);
    List<Contract> findByTo(String to);
}
