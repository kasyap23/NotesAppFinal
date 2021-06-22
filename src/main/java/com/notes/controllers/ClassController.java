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

import java.security.*;
import java.util.*;

//import com.notes.services.ServiceClass;
//import org.springframework.security.authentication.AuthenticationToken;


@RestController
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ClassController {


    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    UserService userService;

    MyUserDetailsService myUserDetailsService;

    JwtTokenProvider jwtTokenProvider;

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

        return ResponseEntity.ok(new com.notes.model.JwtResponseToken(token));
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

    @GetMapping("/signin")
    public ResponseEntity login(Principal principal) {


//            User userDetails = userService.getUserByEmail(user.getEmail());
        if (principal == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

//            if(encoder().matches(userDetails.getPassword(),user.getPassword()))
//            {


        Map<String, Object> map = new HashMap<>();

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;

        System.out.println(token.getName());

        User u = userService.getUserByEmail(token.getName());

        map.put("uid", u.getUid());
        map.put("first_name", u.getFirst_name());
        map.put("last_name", u.getLast_name());
        map.put("email", u.getEmail());


        System.out.println(u);
        System.out.println(new Date(System.currentTimeMillis() + 1000));

        String JwtToken = jwtTokenProvider.generateToken(token);

        System.out.println("true");
        map.put("token", JwtToken);

        return new ResponseEntity(map, HttpStatus.OK);

//                catch(Exception e){
//                    System.out.println(8);
//                    System.out.println(e);
//                }
//                
//                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

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
