package br.com.nexus.controller;

import br.com.nexus.dto.UsuarioLoginDTO;
import br.com.nexus.infra.dao.UsuarioDAO;
import br.com.nexus.model.Repositorio;
import br.com.nexus.model.RepositorioUsuario;
import br.com.nexus.model.Usuario;
import br.com.nexus.service.UsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    private RepositorioUsuario usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioService = new UsuarioService(usuarioDAO);
    }

    @POST
    @Path("/cadastro")
    public Response persistirUsuario(Usuario usuario) {
        try {
            usuarioService.persistirUsuario(usuario);
            usuarioDAO.fecharConexao();
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    public Response fazerLogin(UsuarioLoginDTO dto) {
        try {
            Usuario usuario = usuarioService.fazerLogin(dto.getEmail(), dto.getSenha());
            usuarioDAO.fecharConexao();
            return Response.status(Response.Status.OK).entity(usuario).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
