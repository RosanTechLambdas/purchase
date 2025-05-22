package com.purshase.Purshase_Api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime timestamp;

    public ErrorResponse(String message, int status , LocalDateTime localDateTime) {
        this.message = message;
        this.statusCode = status;
        this.timestamp = localDateTime;
    }
}
