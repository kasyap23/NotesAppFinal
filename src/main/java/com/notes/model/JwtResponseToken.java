package com.notes.model;

import java.io.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class JwtResponseToken implements Serializable {
    private String token;
    private Integer uid;
}
