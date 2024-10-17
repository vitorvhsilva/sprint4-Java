package br.com.nexus.service;

import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.domain.model.Orcamento;
import br.com.nexus.domain.repository.RepositorioOrcamentos;

import java.time.LocalDateTime;
import java.util.List;

public class OrcamentoService {
    private RepositorioOrcamentos repositorioOrcamentos;

    public OrcamentoService(RepositorioOrcamentos repositorioOrcamentos) {
        this.repositorioOrcamentos = repositorioOrcamentos;
    }


    public Orcamento persistirOrcamento(Diagnostico diagnostico) {
        //preco vindo da ia
        Orcamento orcamento = new Orcamento(0.0, LocalDateTime.now(), diagnostico.getIdVeiculo(), diagnostico.getIdDescricaoProblema());
        repositorioOrcamentos.persistirDado(orcamento);
        repositorioOrcamentos.fecharConexao();
        return orcamento;
    }

    public List<Orcamento> pegarOrcamentosPorVeiculo(Long idVeiculo) {
        List<Orcamento> orcamentos = repositorioOrcamentos.pegarOrcamentos(idVeiculo);
        repositorioOrcamentos.fecharConexao();
        return orcamentos;
    }
}
