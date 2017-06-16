package com.freela.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gabriel on 2016-10-27.
 */
public class Empresa extends Usuario implements Serializable {
    private List<Oportunidade> oportunidades;

    public List<Oportunidade> getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(List<Oportunidade> oportunidades) {
        this.oportunidades = oportunidades;
    }
}
