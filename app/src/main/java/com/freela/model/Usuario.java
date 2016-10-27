package com.freela.model;

import java.io.Serializable;

/**
 * Created by Gabriel on 27/10/2016.
 */

public class Usuario implements Serializable {
    private String email;
    private String senha;
    private String nome;
    private Localizacao localizacao;

    public Usuario() {}

    public  Usuario(String email, String senha, String nome, Localizacao localizacao) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
