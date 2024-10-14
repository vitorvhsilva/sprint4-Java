package br.com.nexus.model;

public interface RepositorioUsuario extends Repositorio{
    boolean usuarioExistePorCpf(String cpf);
    boolean usuarioExistePorEmail(String email);
    Long retornarIdPorCpf(String cpf);
    Usuario retornarUsuarioPorLogin(String email, String senha);
}
