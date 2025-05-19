package com.purshase.Purshase_Api.controller;

import com.purshase.Purshase_Api.Request.PurshaseDetailRequest;
import com.purshase.Purshase_Api.response.AppResponse;
import com.purshase.Purshase_Api.service.PurshaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/purshase")
public class PurshaseController {

    @Autowired
    private PurshaseService purshaseService;

    @PostMapping
    public ResponseEntity<?> createPurshase(@RequestBody PurshaseDetailRequest request){
        purshaseService.savePurshase(request);
        Map<String, Object> response=new HashMap<>();
        response.put("message","Purshase Created Successfully");
        return AppResponse.successResponse(HttpStatus.CREATED,response);
    }

    @GetMapping
    public ResponseEntity<?> getAllPurshase(){
        Map<String, Object> response=new HashMap<>();
        response.put("message",purshaseService.getPurchaseDetail());
        return AppResponse.successResponse(HttpStatus.OK,response);
    }
}
