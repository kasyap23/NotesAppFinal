package com.notes.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notes.repository.NoteRepository;
import com.notes.repository.UserRepository;
import com.notes.tables.Note;
import com.notes.tables.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	NoteRepository noteRepo;
        
        @Override
        public User getUserByEmail(String email)
        {
            Iterable<User> users = this.userRepo.findAll();
            for(User u: users)
            {
                if(u.getEmail().equals(email))
                {
                    return u;
                }
            }
            return null;
            
        }
        @Override
	public void delete(int uid, int nid) {
		User user = userRepo.findById(uid).get();
		Set<Note> notes = user.getNotes();
		for(Note n :notes) {
			
			if(n.getNote_id()==nid) {
				noteRepo.deleteById(nid);
			}
		}
		
	}
        @Override
	public Set<Note> getNotesById(int uid) {
	
		User user = userRepo.findById(uid).get();
		Set<Note> notes = user.getNotes();

		return notes;
	
	}
        @Override
	public void saveOrUpdate(Note note, int uid) {
		Optional<User> userOptional = userRepo.findById(uid);
		if(!userOptional.isPresent())  
		{  
			return;  
		}  
		User userData=userOptional.get(); 
		note.setUser(userData);
		noteRepo.save(note);  
		
	}
	public void saveUser(User user) {
		userRepo.save(user);	
	}


}
