package br.com.nexus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioLoginDTO {
    @JsonProperty
    private String email;
    @JsonProperty
    private String senha;

    public UsuarioLoginDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
