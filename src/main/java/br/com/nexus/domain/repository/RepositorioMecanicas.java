package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.Mecanica;

import java.util.List;

public interface RepositorioMecanicas extends Repositorio{
    List<Mecanica> pegarMecanicasPorBairro(String bairro);
    Long retornarIdPeloNome(String nomeMecanica);
}
