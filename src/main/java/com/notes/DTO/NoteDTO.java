/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notes.DTO;

import com.notes.model.User;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Kasyap
 */
@Getter @Setter
public class NoteDTO {
    private int nid;
    private String title,content,remainder_date;
    private Date created_date;
    boolean remainder;
    private int uid;
    
}
