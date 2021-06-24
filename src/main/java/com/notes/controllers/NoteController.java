
package com.notes.controllers;

import com.notes.DTO.NoteDTO;
import com.notes.DTO.NoteMapper;
import com.notes.services.NoteService;
import com.notes.model.Note;
import com.notes.model.User;
import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;
import com.notes.services.UserService;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
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
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class NoteController {
    

    private final NoteService noteService;
    private final UserService userService;
    private final NoteMapper noteMapper;

    
    //Create Note
    @RequestMapping(value = "/",method=RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNote(@RequestBody NoteDTO noteDto)
    {
        Note note = noteMapper.noteDtoToNote(noteDto);
        try
        {
            noteService.save(note);
        }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    
    //GetNote
    @RequestMapping( value = "/",
                     method=RequestMethod.GET,
                     produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllNotes(Authentication auth)
    {
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        Set<NoteDTO> notesDTO = noteMapper.noteToNoteDto(noteService.getNoteByUser(user));
        return new ResponseEntity(notesDTO,HttpStatus.OK);
        
    }
        //GetNote
    @RequestMapping( value = "/{nid}",
                     method=RequestMethod.GET,
                     produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNote(@PathVariable("nid")int nid)
    {

        Note note = noteService.getNoteByNid(nid);
        NoteDTO noteDto = noteMapper.noteToNoteDto(note);
        return new ResponseEntity(noteDto,HttpStatus.OK);
        
    }
    //Update Note
    @RequestMapping(value = "/{nid}",method=RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNotes(@PathVariable("nid") int nid,@RequestBody NoteDTO noteDto)
    {
        Note note = noteMapper.noteDtoToNote(noteDto);
        noteService.update(note);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Delete Note
    @RequestMapping(value = "/{nid}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteNotes(@PathVariable int nid)
    {
        
        noteService.delete(nid);
        return new ResponseEntity(HttpStatus.OK);
    }
}
