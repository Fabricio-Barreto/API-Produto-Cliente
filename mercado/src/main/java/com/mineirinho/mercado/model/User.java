package com.mineirinho.mercado.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
@Data
public class User implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 55)
    private String name;
    @Column(nullable = false, length = 55)
    private String login;
    @Column(nullable = false, length = 55)
    private String password;
    @Column(nullable = false, length = 55)
    private String email;

}
