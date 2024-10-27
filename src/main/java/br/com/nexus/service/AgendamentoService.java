package br.com.nexus.service;

import br.com.nexus.domain.model.Agendamento;
import br.com.nexus.domain.model.Orcamento;
import br.com.nexus.domain.repository.RepositorioAgendamentos;
import br.com.nexus.domain.repository.RepositorioHorarioMecanicas;
import br.com.nexus.domain.repository.RepositorioOrcamentos;
import br.com.nexus.domain.repository.RepositorioVeiculos;
import br.com.nexus.dto.AgendamentoInputDTO;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoService {
    private RepositorioAgendamentos repositorioAgendamentos;
    private RepositorioHorarioMecanicas repositorioHorarioMecanicas;
    private RepositorioOrcamentos repositorioOrcamentos;
    private RepositorioVeiculos repositorioVeiculos;

    public AgendamentoService(RepositorioAgendamentos repositorioAgendamentos, RepositorioHorarioMecanicas repositorioHorarioMecanicas, RepositorioOrcamentos repositorioOrcamentos, RepositorioVeiculos repositorioVeiculos) {
        this.repositorioAgendamentos = repositorioAgendamentos;
        this.repositorioHorarioMecanicas = repositorioHorarioMecanicas;
        this.repositorioOrcamentos = repositorioOrcamentos;
        this.repositorioVeiculos = repositorioVeiculos;
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

    public Agendamento persistirAgendamento(AgendamentoInputDTO dto) {
        Long idVeiculo = repositorioVeiculos.pegarIdPelaPlaca(dto.getPlacaVeiculo());
        Long idOrcamento = repositorioOrcamentos.pegarOrcamentoPorDiagnostico(dto.getIdDiagnostico());
        Long idHorarioMecanica = repositorioHorarioMecanicas.pegarIdHorarioPorMecanicaEDateTime(dto.getIdMecanica(), dto.getHorarioDisponivel());
        LocalDateTime horario = repositorioHorarioMecanicas.pegarHorarioPorId(idHorarioMecanica);

        Agendamento agendamento = new Agendamento(idVeiculo, dto.getIdMecanica(),
                idOrcamento, idHorarioMecanica, horario);
        repositorioAgendamentos.persistirDado(agendamento);
        fecharConexoes();
        return agendamento;
    }

    private void fecharConexoes() {
        repositorioAgendamentos.fecharConexao();
        repositorioHorarioMecanicas.fecharConexao();
        repositorioVeiculos.fecharConexao();
        repositorioOrcamentos.fecharConexao();
    }
}
