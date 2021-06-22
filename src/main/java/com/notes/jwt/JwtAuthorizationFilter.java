/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.jwt;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author Kasyap
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

//    
//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,JwtTokenProvider tokenProvider) {
//        super(authenticationManager);
//        jwtTokenProvider = tokenProvider;    
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = jwtTokenProvider.authenticateToken(request);

        if (authentication != null && jwtTokenProvider.validateToken(request)) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);

    }


}
