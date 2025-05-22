package com.purshase.Purshase_Api.controller;

import com.purshase.Purshase_Api.Request.PurshaseDetailRequest;
import com.purshase.Purshase_Api.model.UserPrincipal;
import com.purshase.Purshase_Api.response.AppResponse;
import com.purshase.Purshase_Api.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<?> createPurchase(@RequestBody PurshaseDetailRequest request){
        purchaseService.savePurshase(request);
        System.out.println("request come");
        Map<String, Object> response=new HashMap<>();
        response.put("purchaseDetails","Purshase Created Successfully");
        return AppResponse.successResponse(HttpStatus.CREATED,response);
    }

    @GetMapping
    public ResponseEntity<?> getAllPurchase(String keyword){
        Map<String, Object> response=new HashMap<>();
        response.put("message",purchaseService.getPurchaseDetail(keyword));
        return AppResponse.successResponse(HttpStatus.OK,response);
    }
}
