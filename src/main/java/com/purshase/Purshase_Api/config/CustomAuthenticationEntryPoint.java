package com.purshase.Purshase_Api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.purshase.Purshase_Api.response.AppResponse;
import com.purshase.Purshase_Api.response.ErrorResponse;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        ErrorResponse errorResponse = new ErrorResponse(
                "Unauthorized: " + authException.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now()
            );

        ResponseUtils.writeErrorResponse(response, errorResponse,HttpStatus.UNAUTHORIZED);
    }
}
