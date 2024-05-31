package com.company.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class _Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_token;
    private String tokenText;
    private Date expiryDate;

    @OneToOne
    @JoinColumn(name = "id_user")
    private _User user;

}

