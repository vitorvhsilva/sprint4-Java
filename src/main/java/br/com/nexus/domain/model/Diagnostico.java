package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Diagnostico {
    @JsonProperty
    private String diagnosticoVeiculo;
    @JsonProperty
    private LocalDateTime dataDiagnostico;
    @JsonProperty
    private Integer feitoDiagnostico;
    @JsonProperty
    private Long idVeiculo;
    @JsonProperty
    private Long idDescricaoProblema;

    public Diagnostico() {
    }

    public Diagnostico(String diagnosticoVeiculo, LocalDateTime dataDiagnostico, Integer feitoDiagnostico, Long idVeiculo, Long idDescricaoProblema) {
        this.diagnosticoVeiculo = diagnosticoVeiculo;
        this.dataDiagnostico = dataDiagnostico;
        this.feitoDiagnostico = feitoDiagnostico;
        this.idVeiculo = idVeiculo;
        this.idDescricaoProblema = idDescricaoProblema;
    }

    public String getDiagnosticoVeiculo() {
        return diagnosticoVeiculo;
    }

    public void setDiagnosticoVeiculo(String diagnosticoVeiculo) {
        this.diagnosticoVeiculo = diagnosticoVeiculo;
    }

    public LocalDateTime getDataDiagnostico() {
        return dataDiagnostico;
    }

    public void setDataDiagnostico(LocalDateTime dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdDescricaoProblema() {
        return idDescricaoProblema;
    }

    public void setIdDescricaoProblema(Long idDescricaoProblema) {
        this.idDescricaoProblema = idDescricaoProblema;
    }

    public Integer getFeitoDiagnostico() {
        return feitoDiagnostico;
    }

    public void setFeitoDiagnostico(Integer feitoDiagnostico) {
        this.feitoDiagnostico = feitoDiagnostico;
    }
}
