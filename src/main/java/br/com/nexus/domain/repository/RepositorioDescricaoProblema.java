package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.DescricaoProblema;

import java.util.List;

public interface RepositorioDescricaoProblema extends Repositorio{
    List<DescricaoProblema> pegarDescricoes(Long idVeiculo);
    Long buscarIdPorVeiculoEDescricao(DescricaoProblema dp);
}
