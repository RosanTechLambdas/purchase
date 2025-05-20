package com.purshase.Purshase_Api.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PurshaseDetailResponse {
    private String customerName;
    private String mobileNumber;
    private String location;
    private int description;
    private LocalDate date;
    private List<String> products;
}
