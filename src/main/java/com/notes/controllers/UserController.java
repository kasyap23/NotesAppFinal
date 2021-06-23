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
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    /* Creating User*/
    @RequestMapping(value = "/save",method=RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
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
    
    /* Get User*/
    @RequestMapping(value = "/get/{email}",
                    method=RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("email") String email)
    {
        if(email==null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        ModelMapper modelMapper = new ModelMapper();
        User user = userService.getUserByEmail(email);
        UserDTO userDTO = userMapper.userToUserDto(user);
        return new ResponseEntity(userDTO,HttpStatus.OK);
        
    }
    
    //Delete User
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email")String email)
    {
        User user = userService.getUserByEmail(email);
        userService.deleteUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    //Update User
    @RequestMapping(value = "/update/{email}",
        method=RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
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
