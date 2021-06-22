/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.repository;

import com.notes.model.*;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteRepository extends CrudRepository<Note,Integer> {

      void deleteNoteByNid(int nid);
    
    
}
