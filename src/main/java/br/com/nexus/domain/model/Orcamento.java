package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Orcamento {
    @JsonProperty
    private Double valorOrcamento;
    @JsonProperty
    private LocalDateTime dataOrcamento;
    @JsonProperty
    private Long idVeiculo;
    @JsonProperty
    private Long idDiagnostico;

    public Orcamento() {
    }

    public Orcamento(Double valorOrcamento, LocalDateTime dataOrcamento, Long idVeiculo, Long idDiagnostico) {
        this.valorOrcamento = valorOrcamento;
        this.dataOrcamento = dataOrcamento;
        this.idVeiculo = idVeiculo;
        this.idDiagnostico = idDiagnostico;
    }

    public Double getValorOrcamento() {
        return valorOrcamento;
    }

    public void setValorOrcamento(Double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public LocalDateTime getDataOrcamento() {
        return dataOrcamento;
    }

    public void setDataOrcamento(LocalDateTime dataOrcamento) {
        this.dataOrcamento = dataOrcamento;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }
}
