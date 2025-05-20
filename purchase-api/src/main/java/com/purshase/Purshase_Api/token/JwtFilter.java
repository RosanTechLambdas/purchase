package com.purshase.Purshase_Api.token;

import com.purshase.Purshase_Api.service.ServiceConfig;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
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

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ServiceConfig serviceConfig;

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
        if(jwtService.isTokenExpired(token)){
            throw new ServletException("Token Expired");
        }

        try {
            if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = serviceConfig.loadUserByUsername(userName);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
        }catch (SignatureException e) {
            throw new ServletException("Invalid JWT signature");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
