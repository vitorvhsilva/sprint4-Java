package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mecanica {
    @JsonProperty
    private String nomeMecanica;

    public Mecanica() {
    }

    public Mecanica(String nomeMecanica) {
        this.nomeMecanica = nomeMecanica;
    }

    public String getNomeMecanica() {
        return nomeMecanica;
    }

    public void setNomeMecanica(String nomeMecanica) {
        this.nomeMecanica = nomeMecanica;
    }
}
