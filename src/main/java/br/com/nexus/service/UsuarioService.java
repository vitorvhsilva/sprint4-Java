package br.com.nexus.service;

import br.com.nexus.dto.UsuarioLoginDTO;
import br.com.nexus.model.RepositorioUsuario;
import br.com.nexus.model.Usuario;

public class UsuarioService {

    private RepositorioUsuario usuarioDAO;

    public UsuarioService(RepositorioUsuario usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void persistirUsuario(Usuario usuario) {
        usuarioDAO.persistirDado(usuario);
        usuarioDAO.fecharConexao();
    }

    public Usuario fazerLogin(UsuarioLoginDTO dto) {
        Usuario usuario = usuarioDAO.retornarUsuarioPorLogin(dto.getEmail(), dto.getSenha());
        usuarioDAO.fecharConexao();
        return usuario;
    }
}
