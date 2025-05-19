package com.purshase.Purshase_Api.controller;

import com.purshase.Purshase_Api.Request.RequestUser;
import com.purshase.Purshase_Api.config.GetCurrentUser;
import com.purshase.Purshase_Api.model.UserPrincipal;
import com.purshase.Purshase_Api.response.AppResponse;
import com.purshase.Purshase_Api.service.UserService;
import com.purshase.Purshase_Api.token.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RequestUser user){
        userService.saveData(user);
        Map<String,Object> response=new HashMap<>();
        response.put("message","User Created Successfully");
        return AppResponse.successResponse(HttpStatus.CREATED,response);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody RequestUser userRequest){
        System.out.println("user request "+userRequest);
        authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(userRequest.getUserName(),userRequest.getPassword()));

        String token=jwtService.generateToken(userRequest.getUserName());
        HashMap<String,Object> response = new HashMap<>();
        response.put("token",token);
        response.put("message","login successful");
        return AppResponse.successResponse(HttpStatus.OK,response);
    }

   }
