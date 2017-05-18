package com.freela.model;

import java.io.Serializable;

/**
 * Created by Gabriel on 17/05/2017.
 */

public class Area implements Serializable {
    private Long id;
    private String nome;

    public Area() {
    }

    public Area(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }
}
