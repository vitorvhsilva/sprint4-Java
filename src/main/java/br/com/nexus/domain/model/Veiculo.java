package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Veiculo {
    @JsonProperty
    private String placaVeiculo;
    @JsonProperty
    private String marcaVeiculo;
    @JsonProperty
    private String modeloVeiculo;
    @JsonProperty
    private String tipoVeiculo;
    @JsonProperty
    private Integer anoVeiculo;
    @JsonProperty
    private Long idUsuario;

    public Veiculo() {
    }

    public Veiculo(String placaVeiculo, String marcaVeiculo, String modeloVeiculo, String tipoVeiculo, Integer anoVeiculo, Long idUsuario) {
        this.placaVeiculo = placaVeiculo;
        this.marcaVeiculo = marcaVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.tipoVeiculo = tipoVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.idUsuario = idUsuario;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public Integer getAnoVeiculo() {
        return anoVeiculo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public void setAnoVeiculo(Integer anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
