package br.com.nexus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AgendamentoInputDTO {
    @JsonProperty
    private String placaVeiculo;
    @JsonProperty
    private Long idMecanica;
    @JsonProperty
    private Long idDiagnostico;
    @JsonProperty
    private LocalDateTime horarioDisponivel;

    public AgendamentoInputDTO() {
    }

    public AgendamentoInputDTO(String placaVeiculo, Long idMecanica, Long idDiagnostico, LocalDateTime horarioDisponivel) {
        this.placaVeiculo = placaVeiculo;
        this.idMecanica = idMecanica;
        this.idDiagnostico = idDiagnostico;
        this.horarioDisponivel = horarioDisponivel;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Long getIdMecanica() {
        return idMecanica;
    }

    public void setIdMecanica(Long idMecanica) {
        this.idMecanica = idMecanica;
    }

    public Long getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public LocalDateTime getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(LocalDateTime horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }
}
