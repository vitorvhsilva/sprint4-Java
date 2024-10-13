package br.com.nexus.controller;

import br.com.nexus.infra.dao.UsuarioDAO;
import br.com.nexus.model.Usuario;
import br.com.nexus.service.UsuarioService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioService = new UsuarioService(usuarioDAO);
    }

    @POST
    public Response persistirUsuario(Usuario usuario) {
        try {
            usuarioService.persistirUsuario(usuario);
            usuarioDAO.fechar();
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
