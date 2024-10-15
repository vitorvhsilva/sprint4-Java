package br.com.nexus.domain.repository;

public interface Repositorio {
    Long obterProximoId();
    <T> void persistirDado(T t);
    void fecharConexao();
}
