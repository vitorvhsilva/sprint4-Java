package br.com.nexus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MecanicaBairroInputDTO {
    @JsonProperty
    private String bairro;

    public MecanicaBairroInputDTO() {
    }

    public MecanicaBairroInputDTO(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
