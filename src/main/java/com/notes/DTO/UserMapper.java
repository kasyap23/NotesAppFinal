/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.DTO;

import com.notes.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;

/**
 *
 * @author Kasyap
 */
@Mapper(uses = {UserMapper.class})
public interface UserMapper {
    
    UserDTO userToUserDto(User user);
}
