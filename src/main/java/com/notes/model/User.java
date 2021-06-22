/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
@NoArgsConstructor @Getter @Setter 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
     private int uid;
    @Column(unique = true, nullable = false)
     private String email;

    @Column(nullable = false)
     private String first_name, last_name, password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Note> notes;

    public User(int uid, String first_name, String last_name,
                String email, String password, LinkedHashSet<Note> notes) {
        this.uid = uid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.notes = notes;
    }

}
