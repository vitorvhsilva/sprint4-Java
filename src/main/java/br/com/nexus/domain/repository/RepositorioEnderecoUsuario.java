package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.EnderecoUsuario;

import java.util.List;

public interface RepositorioEnderecoUsuario extends Repositorio{
    List<EnderecoUsuario> pegarEnderecosPorUsuario(Long idUsuario);
}
