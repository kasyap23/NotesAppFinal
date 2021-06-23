/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.services;

import com.notes.model.*;
import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Kasyap
 */

@Service
@Transactional
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;


    public User getUserByEmail(String email) {
        User user = this.userRepository.getUserByEmail(email);
        if(user == null)
            return null;
        if (user.getEmail().equals(email)) {
            return user;
        }
        return null;
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getUid());
    }
    
}
