package br.com.nexus.service;

import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.domain.model.Orcamento;
import br.com.nexus.domain.repository.RepositorioDiagnosticos;
import br.com.nexus.domain.repository.RepositorioOrcamentos;
import br.com.nexus.domain.repository.RepositorioVeiculos;

import java.time.LocalDateTime;
import java.util.List;

public class OrcamentoService {
    private RepositorioOrcamentos repositorioOrcamentos;
    private RepositorioDiagnosticos repositorioDiagnosticos;
    private RepositorioVeiculos repositorioVeiculos;

    public OrcamentoService(RepositorioOrcamentos repositorioOrcamentos, RepositorioDiagnosticos repositorioDiagnosticos, RepositorioVeiculos repositorioVeiculos) {
        this.repositorioOrcamentos = repositorioOrcamentos;
        this.repositorioDiagnosticos = repositorioDiagnosticos;
        this.repositorioVeiculos = repositorioVeiculos;
    }

    public Orcamento persistirOrcamento(Diagnostico diagnostico) {
        //preco vindo da ia
        Orcamento orcamento = new Orcamento(0.0, LocalDateTime.now(), diagnostico.getIdVeiculo(), diagnostico.getIdDescricaoProblema());
        repositorioOrcamentos.persistirDado(orcamento);
        repositorioDiagnosticos.atualizarDiagnostico(diagnostico);
        fecharConexoes();
        return orcamento;
    }

    public List<Orcamento> pegarOrcamentosPorVeiculo(String placa) {
        Long idVeiculo = repositorioVeiculos.pegarIdPelaPlaca(placa);
        List<Orcamento> orcamentos = repositorioOrcamentos.pegarOrcamentos(idVeiculo);
        fecharConexoes();
        return orcamentos;
    }

    private void fecharConexoes() {
        repositorioOrcamentos.fecharConexao();
        repositorioDiagnosticos.fecharConexao();
        repositorioVeiculos.fecharConexao();
    }
}
