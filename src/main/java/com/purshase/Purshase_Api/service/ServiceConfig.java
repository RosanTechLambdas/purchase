package com.purshase.Purshase_Api.service;

import com.purshase.Purshase_Api.model.User;
import com.purshase.Purshase_Api.model.UserPrincipal;
import com.purshase.Purshase_Api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceConfig implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User existUser= userRepo.findByUserName(s);
        if(existUser==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrincipal(existUser);
    }
}
