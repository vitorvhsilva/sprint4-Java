package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.Usuario;

public interface RepositorioUsuario extends Repositorio{
    boolean usuarioExistePorCpf(String cpf);
    boolean usuarioExistePorEmail(String email);
    Long retornarIdPorCpf(String cpf);
    Usuario retornarUsuarioPorLogin(String email, String senha);
}
