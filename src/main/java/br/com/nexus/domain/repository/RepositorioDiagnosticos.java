package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.Diagnostico;

import java.util.List;

public interface RepositorioDiagnosticos extends Repositorio{
    List<Diagnostico> pegarDiagnosticos(long idVeiculo);
    Long buscarIdPorDataEIds(Diagnostico diagnostico);
    List<Diagnostico> pegarDiagnosticosPorPlaca(String placa);
}
