/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import javax.transaction.Transactional;


@Entity
@Table(name = "user")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
     private int uid;
    @Column(unique = true, nullable = false)
     private String email;

    @Column(nullable = false)
     private String first_name, last_name, password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Note> notes;
    


}
