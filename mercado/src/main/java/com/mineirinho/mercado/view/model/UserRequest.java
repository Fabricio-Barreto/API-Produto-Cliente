package com.mineirinho.mercado.view.model;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
}
