/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.DTO;

import com.notes.model.Note;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kasyap
 */
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteDTO noteToNoteDto(Note note) {
        NoteDTO noteDto = new NoteDTO();
        noteDto.setNid(note.getNid());
        noteDto.setTitle(note.getTitle());
        noteDto.setContent(note.getContent());
        noteDto.setCreated_date(note.getCreated_date());
        noteDto.setRemainder(note.isRemainder());
        noteDto.setRemainder_date(note.getRemainder_date());
        noteDto.setUid(note.getUser().getUid());
        return noteDto;
    
    }

    @Override
    public Set<NoteDTO> noteToNoteDto(Set<Note> notes) {
        Set<NoteDTO> result = new LinkedHashSet<NoteDTO>();
        notes.forEach(note -> {
            result.add(this.noteToNoteDto(note));
        });
        return result;
    }
    
}
