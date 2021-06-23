
package com.notes.model;


import lombok.*;
import javax.persistence.*;
import java.io.*;
import javax.transaction.Transactional;

@Entity
@Table(name = "note")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Note implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_generator")
    @SequenceGenerator(name = "note_generator", sequenceName = "note_seq")
    private int nid;
     
    private String title;
<<<<<<< HEAD
    @Getter @Setter private String content;
    @Getter @Setter private String created_date;
    @Getter @Setter private String remainder_date;
    @Getter @Setter private boolean remainder;
=======
    private String content;
    private String created_date;
    private String remainder_date;
    boolean remainder;
>>>>>>> main
    @ManyToOne
    @JoinColumn(name = "uid")

    private User user;


}
