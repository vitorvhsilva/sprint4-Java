package br.com.nexus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioeVeiculoOutputDTO {
    @JsonProperty
    private Long idUsuario;
    @JsonProperty
    private Long idVeiculo;

    public UsuarioeVeiculoOutputDTO() {
    }

    public UsuarioeVeiculoOutputDTO(Long idUsuario, Long idVeiculo) {
        this.idUsuario = idUsuario;
        this.idVeiculo = idVeiculo;
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
