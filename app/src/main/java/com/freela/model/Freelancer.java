package com.freela.model;

import java.io.Serializable;

/**
 * Created by Gabriel on 2016-10-27.
 */
public class Freelancer extends Usuario implements Serializable  {
    private String profissao;
    private int ImageResourceId;
    private int isFav;
    private int isTurned;

    public Freelancer() {
    }

    public Freelancer(int id, String email, String nome, String senha, String profissao, int imageResourceId, int isFav, int isTurned) {
        super(id, email, nome, senha);
        this.profissao = profissao;
        ImageResourceId = imageResourceId;
        this.isFav = isFav;
        this.isTurned = isTurned;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public int getImageResourceId() {
        return ImageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        ImageResourceId = imageResourceId;
    }

    public int getIsFav() {
        return isFav;
    }

    public void setIsFav(int isFav) {
        this.isFav = isFav;
    }

    public int getIsTurned() {
        return isTurned;
    }

    public void setIsTurned(int isTurned) {
        this.isTurned = isTurned;
    }
}
