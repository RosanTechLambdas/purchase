package com.purshase.Purshase_Api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.purshase.Purshase_Api.response.AppResponse;
import com.purshase.Purshase_Api.response.ErrorResponse;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static void writeErrorResponse(HttpServletResponse response, ErrorResponse error,HttpStatus ErrorStatus) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("result", null);
        map.put("error", error);
        map.put("status", ErrorStatus);
        map.put("statusCode", error.getStatusCode());
        response.setStatus(error.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(map);
        response.getWriter().write(json);
    }


}
