/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.controllers;

import com.notes.DTO.*;
import com.notes.model.*;
import com.notes.repository.*;
import com.notes.services.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Kasyap
 */
@RestController
@RequestMapping(value = "/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping( "/save")
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
        System.out.println("Post Enter");
        if(userService.getUserByEmail(user.getEmail())!=null)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        
        userRepository.save(user);
        System.out.println("user saved");
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/get/{email}")
    public ResponseEntity<?> getUser(@PathVariable("email") String email)
    {
        if(email==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        ModelMapper modelMapper = new ModelMapper();
        User user = userService.getUserByEmail(email);
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return new ResponseEntity(userDTO,HttpStatus.OK);
        
    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email")String email)
    {
        User user = userService.getUserByEmail(email);
        userService.deleteUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateUser(@PathVariable("email") String email,@RequestBody User newUser)
    {
        User oldUser = userService.getUserByEmail(email);
        oldUser.setEmail(newUser.getEmail());
        oldUser.setFirst_name(newUser.getFirst_name());
        oldUser.setLast_name(newUser.getLast_name());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userService.saveUser(oldUser);
        return new ResponseEntity(HttpStatus.OK);
    }

}
