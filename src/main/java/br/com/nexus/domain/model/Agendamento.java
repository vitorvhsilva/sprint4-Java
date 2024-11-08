package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Agendamento {
    @JsonProperty
    private Long idVeiculo;
    @JsonProperty
    private Long idMecanica;
    @JsonProperty
    private Long idOrcamento;
    @JsonProperty
    private Long idHorarioMecanica;
    @JsonProperty
    private LocalDateTime diaDataAgendamento;

    public Agendamento() {
    }

    public Agendamento(Long idVeiculo, Long idMecanica, Long idOrcamento, Long idHorarioMecanica, LocalDateTime diaDataAgendamento) {
        this.idVeiculo = idVeiculo;
        this.idMecanica = idMecanica;
        this.idOrcamento = idOrcamento;
        this.idHorarioMecanica = idHorarioMecanica;
        this.diaDataAgendamento = diaDataAgendamento;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdMecanica() {
        return idMecanica;
    }

    public void setIdMecanica(Long idMecanica) {
        this.idMecanica = idMecanica;
    }

    public Long getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Long getIdHorarioMecanica() {
        return idHorarioMecanica;
    }

    public void setIdHorarioMecanica(Long idHorarioMecanica) {
        this.idHorarioMecanica = idHorarioMecanica;
    }

    public LocalDateTime getDiaDataAgendamento() {
        return diaDataAgendamento;
    }

    public void setDiaDataAgendamento(LocalDateTime diaDataAgendamento) {
        this.diaDataAgendamento = diaDataAgendamento;
    }
}
