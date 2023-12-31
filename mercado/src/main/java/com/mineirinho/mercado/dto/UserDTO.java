package com.mineirinho.mercado.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
}
