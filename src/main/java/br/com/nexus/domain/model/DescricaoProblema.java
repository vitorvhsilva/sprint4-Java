package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class DescricaoProblema {
    @JsonProperty
    private String descricaoProblema;
    @JsonProperty
    private LocalDateTime dataProblema;
    @JsonProperty
    private Long idUsuario;
    @JsonProperty
    private Long idVeiculo;

    public DescricaoProblema() {
    }

    public DescricaoProblema(String descricaoProblema, LocalDateTime dataProblema, Long idUsuario, Long idVeiculo) {
        this.descricaoProblema = descricaoProblema;
        this.dataProblema = dataProblema;
        this.idUsuario = idUsuario;
        this.idVeiculo = idVeiculo;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public LocalDateTime getDataProblema() {
        return dataProblema;
    }

    public void setDataProblema(LocalDateTime dataProblema) {
        this.dataProblema = dataProblema;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
}
