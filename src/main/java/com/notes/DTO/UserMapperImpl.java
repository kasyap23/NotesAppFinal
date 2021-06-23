/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.DTO;

import com.notes.model.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kasyap
 */
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setUid(user.getUid());
        userDto.setFirst_name(user.getFirst_name());
        userDto.setLast_name(user.getLast_name());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    
}
