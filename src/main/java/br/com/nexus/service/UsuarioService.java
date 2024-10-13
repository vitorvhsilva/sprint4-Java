package br.com.nexus.service;

import br.com.nexus.infra.dao.UsuarioDAO;
import br.com.nexus.model.Usuario;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void persistirUsuario(Usuario usuario) {
        usuarioDAO.persistirUsuario(usuario);
    }
}
