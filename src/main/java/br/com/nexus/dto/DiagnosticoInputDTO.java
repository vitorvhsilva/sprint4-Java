package br.com.nexus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiagnosticoInputDTO {
    @JsonProperty
    private String diagnosticoVeiculo;
    @JsonProperty
    private Long idVeiculo;
    @JsonProperty
    private Long idDescricaoProblema;

    public DiagnosticoInputDTO() {
    }

    public DiagnosticoInputDTO(String diagnosticoVeiculo, Long idVeiculo, Long idDescricaoProblema) {
        this.diagnosticoVeiculo = diagnosticoVeiculo;
        this.idVeiculo = idVeiculo;
        this.idDescricaoProblema = idDescricaoProblema;
    }

    public String getDiagnosticoVeiculo() {
        return diagnosticoVeiculo;
    }

    public void setDiagnosticoVeiculo(String diagnosticoVeiculo) {
        this.diagnosticoVeiculo = diagnosticoVeiculo;
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
}
