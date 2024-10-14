package br.com.nexus.model;

public interface Repositorio {
    Long obterProximoId();
    <T> void persistirDado(T t);
    void fecharConexao();
}
