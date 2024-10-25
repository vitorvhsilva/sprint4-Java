package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Veiculo {
    @JsonProperty
    private String placa;
    @JsonProperty
    private String marca;
    @JsonProperty
    private String modelo;
    @JsonProperty
    private String tipo;
    @JsonProperty
    private Integer ano;
    @JsonProperty
    private Long idUsuario;

    public Veiculo() {
    }

    public Veiculo(String placa, String marca, String modelo, String tipo, Integer ano, Long idUsuario) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.ano = ano;
        this.idUsuario = idUsuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ano=" + ano +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
