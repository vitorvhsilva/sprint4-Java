package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.Veiculo;
import br.com.nexus.dto.UsuarioeVeiculoOutputDTO;

import java.util.List;

public interface RepositorioVeiculos extends Repositorio{
    List<Veiculo> pegarVeiculos(Long idUsuario);
    Long pegarIdPelaPlaca(String placa);
    UsuarioeVeiculoOutputDTO pegarUsuarioEVeiculoPelaPlaca(String placa);
}
