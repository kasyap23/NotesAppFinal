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


    public void delete(int nid) {
            noteRepository.deleteNoteByNid(nid);
    }
    
    public Note getNoteByNid(int nid)
    {
        Note note = noteRepository.getNoteByNid(nid);
        return note;
    }
    public Set<Note> getNotesById(int uid) {
        User user = userRepository.findById(uid).get();
        Set<Note> notes = user.getNotes();
        return notes;

    }


    public void update(Note note) {
        Optional<User> userOptional = userRepository.findById(note.getUser().getUid());
        if (!userOptional.isPresent()) {
            return;
        }
        note.setUser(userOptional.get());
        noteRepository.save(note);
    }
    public void save(Note note) throws Exception
    {
        Optional<User> userOptional = userRepository.findById(note.getUser().getUid());
        if(!userOptional.isPresent())
        {
            throw new Exception("User Not found");
            
        }
        User userData = userOptional.get();
        note.setUser(userData);
        noteRepository.save(note);
            
    }

    public Set<Note> getNoteByUser(User user) {
        Set<Note> notes = noteRepository.getNoteByUser(user);
        return notes;
    }
    
}
