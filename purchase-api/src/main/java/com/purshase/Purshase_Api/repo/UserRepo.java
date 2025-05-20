package com.purshase.Purshase_Api.repo;

import com.purshase.Purshase_Api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    User findByUserName(String s);
}
