package br.com.nexus.service;

import br.com.nexus.model.RepositorioUsuario;
import br.com.nexus.model.Usuario;

public class UsuarioService {

    private RepositorioUsuario usuarioDAO;

    public UsuarioService(RepositorioUsuario usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void persistirUsuario(Usuario usuario) {

        if (usuarioDAO.usuarioExistePorEmail(usuario.getEmail())) {
            throw new RuntimeException("Usuário já existe pelo Email!");
        }

        if (usuarioDAO.usuarioExistePorCpf(usuario.getCpf())) {
            throw new RuntimeException("Usuário já existe pelo CPF!");
        }

        usuarioDAO.persistirDado(usuario);
    }

    public Usuario fazerLogin(String email, String senha) {
        return usuarioDAO.retornarUsuarioPorLogin(email, senha);
    }
}
