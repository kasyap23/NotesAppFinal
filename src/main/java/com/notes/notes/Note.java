package com.notes.notes;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kasyap
 */
@Entity
@Table(name="note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int note_id;
    private String title,content,created_date,remainder_date;
    boolean remainder;
    
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    
    Note(){}

    public Note(int note_id, String title, String content, String created_date, String remainder_date, boolean remainder) {
        this.note_id = note_id;
        this.title = title;
        this.content = content;
        this.created_date = created_date;
        this.remainder_date = remainder_date;
        this.remainder = remainder;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public void setRemainder_date(String remainder_date) {
        this.remainder_date = remainder_date;
    }

    public void setRemainder(boolean remainder) {
        this.remainder = remainder;
    }

    public int getNote_id() {
        return note_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getRemainder_date() {
        return remainder_date;
    }

    public boolean isRemainder() {
        return remainder;
    }
    
    
    
}
