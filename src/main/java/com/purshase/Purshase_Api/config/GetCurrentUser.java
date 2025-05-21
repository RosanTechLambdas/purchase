package com.purshase.Purshase_Api.config;

import com.purshase.Purshase_Api.model.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetCurrentUser {

    public static String getCurrentUserId() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userPrincipal.getUserId();
         return userId;
    }
}
