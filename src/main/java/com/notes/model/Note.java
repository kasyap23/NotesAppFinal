
package com.notes.model;


import lombok.*;
import javax.persistence.*;
import java.io.*;
import java.util.Date;
import javax.transaction.Transactional;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "note")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Note implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_generator")
    @SequenceGenerator(name = "note_generator", sequenceName = "note_seq",allocationSize=1)
    private int nid;
     
    private String title;

    @Getter @Setter private String content;
    @Column(updatable = false)
    @CreationTimestamp @Temporal(TemporalType.DATE)
    @Getter @Setter private Date created_date;
    @Getter @Setter private String remainder_date;
    @Getter @Setter private boolean remainder;

    @ManyToOne
    @JoinColumn(name = "uid")

    private User user;


}
