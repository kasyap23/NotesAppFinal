package com.notes;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@SpringBootApplication
public class NotesApplication {

	public static void main(String[] args) {
		System.out.println("Hello Deepak");
		SpringApplication.run(NotesApplication.class, args);

	}


}
