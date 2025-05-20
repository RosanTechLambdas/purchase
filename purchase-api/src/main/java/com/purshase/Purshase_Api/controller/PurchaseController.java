package com.purshase.Purshase_Api.controller;

import com.purshase.Purshase_Api.Request.PurshaseDetailRequest;
import com.purshase.Purshase_Api.response.AppResponse;
import com.purshase.Purshase_Api.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Map<String, Object> response=new HashMap<>();
        response.put("purchaseDetails","Purshase Created Successfully");
        return AppResponse.successResponse(HttpStatus.CREATED,response);
    }

    @GetMapping
    public ResponseEntity<?> getAllPurchase(){
        Map<String, Object> response=new HashMap<>();
        response.put("message",purchaseService.getPurchaseDetail());
        return AppResponse.successResponse(HttpStatus.OK,response);
    }
}
