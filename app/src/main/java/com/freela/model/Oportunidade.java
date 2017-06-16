package com.freela.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gabriel on 16/11/2016.
 */

public class Oportunidade implements Serializable {
    private int id;
    private String titulo;
    private String descricao;
    private Date dtInicio;
    private Date dtFim;
    private int ImageResourceId;
    private int isFav;
    private int isTurned;
    private Area area;


    public Oportunidade() {
    }

    public Oportunidade(int id, String titulo, String descricao, Date dtInicio, Date dtFim, int imageResourceId, int isFav, int isTurned, Area area) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        ImageResourceId = imageResourceId;
        this.isFav = isFav;
        this.isTurned = isTurned;
        this.area = area;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public int getImageResourceId() { return ImageResourceId; }

    public void setImageResourceId(int imageResourceId) { ImageResourceId = imageResourceId; }

    public int getIsFav() { return isFav; }

    public void setIsFav(int isFav) { this.isFav = isFav; }

    public int getIsTurned() { return isTurned; }

    public void setIsTurned(int isTurned) { this.isTurned = isTurned; }

    public Area getArea() { return area; }

    public void setArea(Area area) { this.area = area; }
}
