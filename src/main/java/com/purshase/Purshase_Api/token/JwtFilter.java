package com.purshase.Purshase_Api.token;

import com.purshase.Purshase_Api.config.ResponseUtils;
import com.purshase.Purshase_Api.response.ErrorResponse;
import com.purshase.Purshase_Api.service.ServiceConfig;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ServiceConfig serviceConfig;

    JwtFilter() {}
    @Override
    protected void doFilterInternal
            (HttpServletRequest httpServletRequest,
             HttpServletResponse httpServletResponse,
             FilterChain filterChain) throws ServletException, IOException {

        String path = httpServletRequest.getServletPath();

        if( path.equals("/login") ||
                path.equals("/register") ||
                path.startsWith("/swagger") ||
                path.startsWith("/v2/api-docs") ||
                path.startsWith("/v3/api-docs") ||
                path.startsWith("/swagger-resources") ||
                path.startsWith("/webjars/") ||
                path.equals("/swagger-ui.html") ||
                path.startsWith("/swagger-ui")){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;

        }

        String token =null;
        String userName = null;
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            userName=jwtService.getUserName(token);
        }
        else
        {
            ErrorResponse errorResponse=new ErrorResponse("jwt token not null",HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
            ResponseUtils.writeErrorResponse(httpServletResponse,errorResponse,HttpStatus.UNAUTHORIZED);
            return;
        }
        if(jwtService.isTokenExpired(token)){
            ErrorResponse errorResponse=new ErrorResponse("Token Expired",HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
            ResponseUtils.writeErrorResponse(httpServletResponse,errorResponse,HttpStatus.UNAUTHORIZED);
            return;
        }


            if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = serviceConfig.loadUserByUsername(userName);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
           else{
            ErrorResponse errorResponse=new ErrorResponse("Invalid JWT signature",HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
            ResponseUtils.writeErrorResponse(httpServletResponse,errorResponse,HttpStatus.UNAUTHORIZED);
            return;
            }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
