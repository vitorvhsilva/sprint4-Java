package br.com.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {
    @JsonProperty
    private String nome;
    @JsonProperty
    private String email;
    @JsonProperty
    private String senha;
    @JsonProperty
    private String genero;
    @JsonProperty
    private String telefone;
    @JsonProperty
    private String cpf;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String genero, String telefone, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getGenero() {
        return genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
