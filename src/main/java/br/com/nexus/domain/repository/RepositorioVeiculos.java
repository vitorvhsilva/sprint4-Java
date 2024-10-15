package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.Veiculo;

import java.util.List;

public interface RepositorioVeiculos extends Repositorio{
    List<Veiculo> pegarVeiculos(Long idUsuario);
    Long pegarIdPelaPlaca(String placa);
}
