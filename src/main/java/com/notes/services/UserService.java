/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.services;

import com.notes.tables.Note;
import com.notes.tables.User;
import java.util.Set;

/**
 *
 * @author Kasyap
 */

public interface UserService {
    public User getUserByEmail(String email);

   public void saveUser(User user);
   public void deleteUser(User user);
    
}