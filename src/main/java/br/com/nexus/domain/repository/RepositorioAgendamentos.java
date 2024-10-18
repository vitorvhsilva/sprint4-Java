package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.Agendamento;

import java.util.List;

public interface RepositorioAgendamentos extends Repositorio{
    List<Agendamento> pegarAgendamentosPorVeiculo(long idVeiculo);
    List<Agendamento> pegarAgendamentosPorMecanica(long idMecanica);
}
