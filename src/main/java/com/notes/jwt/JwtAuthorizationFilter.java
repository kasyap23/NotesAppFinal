/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author Kasyap
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        System.out.println("--------------------------------------------");
        System.out.println(path);
        return "/test".equals(path);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = jwtTokenProvider.authenticateToken(request);
        if(authentication!=null && jwtTokenProvider.validateToken(request))
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response); 
         
    }
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,JwtTokenProvider tokenProvider) {
        super(authenticationManager);
        jwtTokenProvider = tokenProvider;
        
        
    }
    
    
}
