package br.com.nexus.service;

import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.domain.repository.RepositorioDiagnosticos;
import br.com.nexus.dto.DiagnosticoInputDTO;

import java.time.LocalDateTime;
import java.util.List;

public class DiagnosticoService {
    private RepositorioDiagnosticos repositorioDiagnosticos;

    public DiagnosticoService(RepositorioDiagnosticos repositorioDiagnosticos) {
        this.repositorioDiagnosticos = repositorioDiagnosticos;
    }


    public Diagnostico persistirDiagnostico(DiagnosticoInputDTO dto) {
        Diagnostico diagnostico = new Diagnostico(dto.getDiagnosticoVeiculo(), LocalDateTime.now(), dto.getIdVeiculo(),
                dto.getIdDescricaoProblema());
        repositorioDiagnosticos.persistirDado(diagnostico);
        repositorioDiagnosticos.fecharConexao();
        return diagnostico;
    }

    public List<Diagnostico> pegarDiagnosticosPorVeiculo(Long idVeiculo) {
        List<Diagnostico> diagnosticos = repositorioDiagnosticos.pegarDiagnosticos(idVeiculo);
        repositorioDiagnosticos.fecharConexao();
        return diagnosticos;
    }
}