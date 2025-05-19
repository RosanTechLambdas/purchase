package com.purshase.Purshase_Api.service;

import com.purshase.Purshase_Api.Mapper.UserMapper;
import com.purshase.Purshase_Api.Request.RequestUser;
import com.purshase.Purshase_Api.model.User;
import com.purshase.Purshase_Api.repo.UserRepo;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    public void saveData(RequestUser user) {
        User newUser=userMapper.toUser(user);
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userRepo.save(newUser);
    }
}
