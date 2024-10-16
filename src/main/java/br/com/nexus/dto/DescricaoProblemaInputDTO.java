package br.com.nexus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DescricaoProblemaInputDTO {
    @JsonProperty
    private String descricao;
    @JsonProperty
    private String placaVeiculo;

    public DescricaoProblemaInputDTO() {
    }

    public DescricaoProblemaInputDTO(String descricao, String placaVeiculo) {
        this.descricao = descricao;
        this.placaVeiculo = placaVeiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }
}
