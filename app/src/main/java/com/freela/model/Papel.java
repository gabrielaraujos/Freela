package com.freela.model;

/**
 * Created by Gabriel on 2016-10-27.
 */
public enum Papel {
    FREELANCER(0), EMPRESA(1);

    private final Integer id;

    private Papel(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
