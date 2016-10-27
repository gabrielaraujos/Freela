package com.freela.model;

import java.io.Serializable;

/**
 * Created by Gabriel on 27/10/2016.
 */

public class Credenciais implements Serializable {
    private String login;
    private String senha;

    public Credenciais(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Credenciais() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
