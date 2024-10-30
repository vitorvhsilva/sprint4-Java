package br.com.nexus.service;

import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.dto.DiagnosticoInputDTO;

import java.util.List;

public interface ServicosDoDiagnostico {
    Diagnostico persistirDiagnostico(DiagnosticoInputDTO dto);
    List<Diagnostico> pegarDiagnosticosPorVeiculo(Long idVeiculo);
}
