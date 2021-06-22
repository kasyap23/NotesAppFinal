
package com.notes.controllers;

import com.notes.services.NoteService;
import com.notes.model.Note;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kasyap
 */
@RestController
@RequestMapping(value = "/notes")
public class NoteController {
    
    @Autowired
    NoteService noteService;
    
    @RequestMapping(value = "/save/{uid}",method=RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNote(@PathVariable("uid") int uid,@RequestBody Note note)
    {
        noteService.saveOrUpdate(note,uid);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @GetMapping("/get/{uid}")
    @RequestMapping( value = "/get/{uid}",
                     method=RequestMethod.GET,
                     produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllNotes(@PathVariable("uid") int uid)
    {
        Set<Note> notes = noteService.getNotesById(uid);
        return new ResponseEntity(notes,HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{uid}",method=RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNotes(@PathVariable("uid") int uid,@RequestBody Note note)
    {
        noteService.saveOrUpdate(note, uid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{uid}/{nid}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteNotes(@PathVariable("uid") int uid, @PathVariable int nid)
    {
        noteService.delete(uid, nid);
        return new ResponseEntity(HttpStatus.OK);
    }
}
