/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.jwt;


import io.jsonwebtoken.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.stereotype.*;

import javax.servlet.http.*;
import java.util.*;
import java.util.stream.*;
import lombok.NoArgsConstructor;

/**
 * @author Kasyap
 */
@Component
@NoArgsConstructor
public class JwtTokenProvider {

    private static String jwtSecret = "TestSigmaKeepSecret";
    private static String jwtTokenPrefix = "Bearer";
    private static String jwtHeader = "Authorization";

    private static long jwtExpirationInMs = 36000000;

    public Authentication authenticateToken(HttpServletRequest request) {
        String token = this.getToken(request);
        if (token == null)
            return null;
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        if (username == null)
            return null;
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public String generateToken(Authentication authentication) {


        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        System.out.println("----------------------------------------------------------");
        System.out.println(authorities);
        System.out.println("----------------------------------------------------------");

        return Jwts.builder().setSubject(authentication.getName())
                .claim("roles", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(jwtHeader);
        if (token == null)
            return null;
        return token.substring(7);
    }

    public boolean validateToken(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) {
            return false;
        }
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        if (claims.getExpiration().before(new Date())) {
            System.out.println("false");

            return false;
        }
        System.out.println("true");
        return true;
    }


}
