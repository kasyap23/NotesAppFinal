/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.services;

import com.notes.model.*;
import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;
import org.springframework.stereotype.*;

import java.util.*;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kasyap
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;


    public void delete(int uid, int nid) {
            noteRepository.deleteNoteByNid(nid);
    }
    

    public Set<Note> getNotesById(int uid) {
        User user = userRepository.findById(uid).get();
        Set<Note> notes = user.getNotes();
        return notes;

    }


    public void saveOrUpdate(Note note, int uid) {
        Optional<User> userOptional = userRepository.findById(uid);
        if (!userOptional.isPresent()) {
            return;
        }
        User userData = userOptional.get();
        note.setUser(userData);
        noteRepository.save(note);
    }
    
}
