package com.felipestag.workShopSpring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.felipestag.workShopSpring.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
