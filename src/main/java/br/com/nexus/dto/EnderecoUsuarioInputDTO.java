package br.com.nexus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoUsuarioInputDTO {
    @JsonProperty
    private String cpfUsuario;
    @JsonProperty
    private String numEnderecoUsuario;
    @JsonProperty
    private String ruaEnderecoUsuario;
    @JsonProperty
    private String cepEnderecoUsuario;
    @JsonProperty
    private String bairroEnderecoUsuario;

    public EnderecoUsuarioInputDTO() {
    }

    public EnderecoUsuarioInputDTO(String cpfUsuario, String numEnderecoUsuario, String ruaEnderecoUsuario, String cepEnderecoUsuario, String bairroEnderecoUsuario) {
        this.cpfUsuario = cpfUsuario;
        this.numEnderecoUsuario = numEnderecoUsuario;
        this.ruaEnderecoUsuario = ruaEnderecoUsuario;
        this.cepEnderecoUsuario = cepEnderecoUsuario;
        this.bairroEnderecoUsuario = bairroEnderecoUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
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
