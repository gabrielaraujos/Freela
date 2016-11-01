package com.freela.model;

import java.io.Serializable;

/**
 * Created by Gabriel on 2016-10-27.
 */
public class Freelancer extends Usuario implements Serializable  {
    private String profissao;

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
}
