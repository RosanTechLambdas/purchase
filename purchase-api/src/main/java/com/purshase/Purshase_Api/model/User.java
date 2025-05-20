package com.purshase.Purshase_Api.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String userId;
    private String userName;
    private String password;
    @CreatedDate
    private LocalDate createdDate;
}
