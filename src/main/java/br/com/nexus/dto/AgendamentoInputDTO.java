package br.com.nexus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AgendamentoInputDTO {
    @JsonProperty
    private Long idVeiculo;
    @JsonProperty
    private Long idMecanica;
    @JsonProperty
    private Long idOrcamento;
    @JsonProperty
    private Long idHorarioMecanica;

    public AgendamentoInputDTO() {
    }

    public AgendamentoInputDTO(Long idVeiculo, Long idMecanica, Long idOrcamento, Long idHorarioMecanica) {
        this.idVeiculo = idVeiculo;
        this.idMecanica = idMecanica;
        this.idOrcamento = idOrcamento;
        this.idHorarioMecanica = idHorarioMecanica;
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
}
