package com.purshase.Purshase_Api.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "purshases")
public class PurshaseDetail {
    @Id
    private String id;
    private String userId;
    private String customerId;
    private String customerName;
    private String mobileNumber;
    private String location;
    private String description;
    private LocalDate date;
    private List<String> products;
    @CreatedDate
    private LocalDate createdDate;
    @CreatedBy
    private String createdBy;
}
