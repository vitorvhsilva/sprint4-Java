package br.com.nexus.service;

import br.com.nexus.domain.model.Agendamento;
import br.com.nexus.domain.repository.RepositorioAgendamentos;

import java.util.List;

public class AgendamentoService {
    private RepositorioAgendamentos repositorioAgendamentos;

    public AgendamentoService(RepositorioAgendamentos repositorioAgendamentos) {
        this.repositorioAgendamentos = repositorioAgendamentos;
    }


    public List<Agendamento> pegarAgendamentosPorVeiculo(Long idVeiculo) {
        List<Agendamento> agendamentos = repositorioAgendamentos.pegarAgendamentosPorVeiculo(idVeiculo);
        repositorioAgendamentos.fecharConexao();
        return agendamentos;
    }

    public List<Agendamento> pegarAgendamentosPorMecanica(Long idMecanica) {
        List<Agendamento> agendamentos = repositorioAgendamentos.pegarAgendamentosPorMecanica(idMecanica);
        repositorioAgendamentos.fecharConexao();
        return agendamentos;
    }

    public void persistirDescricao(Agendamento agendamento) {
        repositorioAgendamentos.persistirDado(agendamento);
        repositorioAgendamentos.fecharConexao();
    }
}
