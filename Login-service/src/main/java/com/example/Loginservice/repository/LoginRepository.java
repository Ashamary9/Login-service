package com.example.Loginservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Loginservice.model.LoginModel;

@Repository
public interface LoginRepository extends MongoRepository<LoginModel, String> {
	LoginModel findByUsername(String username);

}
