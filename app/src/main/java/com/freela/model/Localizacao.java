package com.freela.model;

import java.io.Serializable;

/**
 * Created by Gabriel on 27/10/2016.
 */

public class Localizacao implements Serializable {
    private String pais;
    private String cidade;
    private String estado;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
