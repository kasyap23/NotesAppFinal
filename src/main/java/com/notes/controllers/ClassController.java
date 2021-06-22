package com.notes.controllers;

import com.notes.jwt.*;
import com.notes.model.*;
import com.notes.repository.*;
import com.notes.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import com.notes.model.JwtResponseToken;

import java.security.*;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//import com.notes.services.ServiceClass;
//import org.springframework.security.authentication.AuthenticationToken;


@RestController
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)

@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ClassController {

    
    private final AuthenticationManager authenticationManager;
    
    @Autowired
    UserRepository userRepository;

    private final UserService userService;

    private final MyUserDetailsService myUserDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/test")
    public User test() {
        return this.userRepository.getUserByEmail("deepak1@gmail.com");
    }

    @PostMapping("authenticate")
    public ResponseEntity createAuthenticationToken(@RequestBody User user) throws Exception {
        UsernamePasswordAuthenticationToken UPAuthToken;
        try {
            UPAuthToken = authenticate(user.getEmail(), user.getPassword());

        } catch (Exception e) {
            System.out.println("Exception" + e);
            throw new Exception(e.getMessage());
        }
        final MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getEmail());
        final String token = jwtTokenProvider.generateToken(UPAuthToken);

        return ResponseEntity.ok(new JwtResponseToken(token,userDetails.getUid()));
    }

    private UsernamePasswordAuthenticationToken authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken UPAuthToken;
        try {
            UPAuthToken = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(UPAuthToken);
            return UPAuthToken;

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User u) {
        User tempUser = userService.getUserByEmail(u.getEmail());
        if (tempUser != null) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        }
        try {
            u.setPassword(encoder().encode(u.getPassword()));
            this.userRepository.save(u);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }


}
