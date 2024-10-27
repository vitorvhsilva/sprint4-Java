package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.Orcamento;

import java.util.List;

public interface RepositorioOrcamentos extends Repositorio {
    List<Orcamento> pegarOrcamentos(long idVeiculo);
    Long pegarOrcamentoPorDiagnostico(long idDiagnostico);
}
