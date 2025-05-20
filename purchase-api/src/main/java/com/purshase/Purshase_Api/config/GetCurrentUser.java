package com.purshase.Purshase_Api.config;

import com.purshase.Purshase_Api.model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GetCurrentUser{

    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
                return ((UserPrincipal) principal).getUserId();
        }

        return null;
    }

}

