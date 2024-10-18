package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoUsuario {
    @JsonProperty
    private Long idUsuario;
    @JsonProperty
    private String numEnderecoUsuario;
    @JsonProperty
    private String ruaEnderecoUsuario;
    @JsonProperty
    private String cepEnderecoUsuario;
    @JsonProperty
    private String bairroEnderecoUsuario;

    public EnderecoUsuario() {
    }

    public EnderecoUsuario(Long idUsuario, String numEnderecoUsuario, String ruaEnderecoUsuario, String cepEnderecoUsuario, String bairroEnderecoUsuario) {
        this.idUsuario = idUsuario;
        this.numEnderecoUsuario = numEnderecoUsuario;
        this.ruaEnderecoUsuario = ruaEnderecoUsuario;
        this.cepEnderecoUsuario = cepEnderecoUsuario;
        this.bairroEnderecoUsuario = bairroEnderecoUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumEnderecoUsuario() {
        return numEnderecoUsuario;
    }

    public void setNumEnderecoUsuario(String numEnderecoUsuario) {
        this.numEnderecoUsuario = numEnderecoUsuario;
    }

    public String getRuaEnderecoUsuario() {
        return ruaEnderecoUsuario;
    }

    public void setRuaEnderecoUsuario(String ruaEnderecoUsuario) {
        this.ruaEnderecoUsuario = ruaEnderecoUsuario;
    }

    public String getCepEnderecoUsuario() {
        return cepEnderecoUsuario;
    }

    public void setCepEnderecoUsuario(String cepEnderecoUsuario) {
        this.cepEnderecoUsuario = cepEnderecoUsuario;
    }

    public String getBairroEnderecoUsuario() {
        return bairroEnderecoUsuario;
    }

    public void setBairroEnderecoUsuario(String bairroEnderecoUsuario) {
        this.bairroEnderecoUsuario = bairroEnderecoUsuario;
    }
}
