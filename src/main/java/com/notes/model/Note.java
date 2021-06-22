
package com.notes.model;


import lombok.*;
import javax.persistence.*;
import java.io.*;
import javax.transaction.Transactional;

@Data
@Entity
@Transactional
@Table(name = "note")
@NoArgsConstructor
public class Note implements Serializable {
    boolean remainder;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private int nid;
    @Getter @Setter 
    private String title;
    @Getter @Setter private String content;
    @Getter @Setter private String created_date;
    @Getter @Setter private String remainder_date;
    @ManyToOne
    @JoinColumn(name = "uid")
    @Setter
    private User user;

    public Note(int note_id, String title, String content,
                String created_date, String remainder_date, boolean remainder) {
        this.nid = note_id;
        this.title = title;
        this.content = content;
        this.created_date = created_date;
        this.remainder_date = remainder_date;
        this.remainder = remainder;
        //this.user = user;
    }

}
