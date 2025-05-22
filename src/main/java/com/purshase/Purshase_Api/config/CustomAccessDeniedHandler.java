package com.purshase.Purshase_Api.config;

import com.purshase.Purshase_Api.response.ErrorResponse;
import io.jsonwebtoken.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, java.io.IOException {

        ErrorResponse errorResponse = new ErrorResponse(
                "Unauthorized: " + accessDeniedException.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now()
        );

        ResponseUtils.writeErrorResponse(response, errorResponse, HttpStatus.FORBIDDEN);
    }
}
