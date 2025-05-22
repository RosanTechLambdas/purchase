package com.purshase.Purshase_Api.exception;

import com.purshase.Purshase_Api.response.AppResponse;
import com.purshase.Purshase_Api.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class    GlobalException {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointException(NullPointerException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return AppResponse.errorResponse(HttpStatus.BAD_REQUEST, errorResponse);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return AppResponse.errorResponse(HttpStatus.NOT_FOUND, errorResponse);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> handleAuthException() {
        ErrorResponse errorResponse = new ErrorResponse("Invalid userName or password",HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return AppResponse.errorResponse(HttpStatus.BAD_REQUEST, errorResponse);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return AppResponse.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return AppResponse.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return AppResponse.errorResponse(HttpStatus.BAD_REQUEST, errorResponse);
    }

}
