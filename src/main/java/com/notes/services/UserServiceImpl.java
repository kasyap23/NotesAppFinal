package com.notes.services;

import com.notes.model.*;
import com.notes.repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    NoteRepository noteRepo;

    @Override
    public User getUserByEmail(String email) {
        User user = this.userRepo.getUserByEmail(email);
        if (user.getEmail().equals(email)) {
            return user;
        }
        return null;

    }


    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void deleteUser(User user) {
        userRepo.deleteById(user.getUid());
    }

}
