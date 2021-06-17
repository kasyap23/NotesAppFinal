/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.notes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kasyap
 */
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    
}
