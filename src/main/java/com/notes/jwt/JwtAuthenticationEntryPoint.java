/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.jwt;


import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.stereotype.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author Kasyap
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}
