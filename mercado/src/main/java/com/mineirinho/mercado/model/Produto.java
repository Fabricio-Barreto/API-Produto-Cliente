package com.mineirinho.mercado.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String nome;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false)
    private Double valor;
    @Column(nullable = false, length = 70)
    private String observacao;

}
