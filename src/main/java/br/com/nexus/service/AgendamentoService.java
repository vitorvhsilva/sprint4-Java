package br.com.nexus.service;

import br.com.nexus.domain.model.Agendamento;
import br.com.nexus.domain.repository.RepositorioAgendamentos;
import br.com.nexus.domain.repository.RepositorioHorarioMecanicas;
import br.com.nexus.dto.AgendamentoInputDTO;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoService {
    private RepositorioAgendamentos repositorioAgendamentos;
    private RepositorioHorarioMecanicas repositorioHorarioMecanicas;

    public AgendamentoService(RepositorioAgendamentos repositorioAgendamentos, RepositorioHorarioMecanicas repositorioHorarioMecanicas) {
        this.repositorioAgendamentos = repositorioAgendamentos;
        this.repositorioHorarioMecanicas = repositorioHorarioMecanicas;
    }

    public List<Agendamento> pegarAgendamentosPorVeiculo(Long idVeiculo) {
        List<Agendamento> agendamentos = repositorioAgendamentos.pegarAgendamentosPorVeiculo(idVeiculo);
        fecharConexoes();
        return agendamentos;
    }

    public List<Agendamento> pegarAgendamentosPorMecanica(Long idMecanica) {
        List<Agendamento> agendamentos = repositorioAgendamentos.pegarAgendamentosPorMecanica(idMecanica);
        fecharConexoes();
        return agendamentos;
    }

    public Agendamento persistirDescricao(AgendamentoInputDTO dto) {
        LocalDateTime horario = repositorioHorarioMecanicas.pegarHorarioPorId(dto.getIdHorarioMecanica());
        Agendamento agendamento = new Agendamento(dto.getIdVeiculo(), dto.getIdMecanica(),
                dto.getIdOrcamento(), dto.getIdHorarioMecanica(), horario);
        repositorioAgendamentos.persistirDado(agendamento);
        fecharConexoes();
        return agendamento;
    }

    private void fecharConexoes() {
        repositorioAgendamentos.fecharConexao();
        repositorioHorarioMecanicas.fecharConexao();
    }
}
