package br.com.nexus.controller;

import br.com.nexus.dto.UsuarioLoginDTO;
import br.com.nexus.infra.dao.UsuarioDAO;
import br.com.nexus.domain.model.Usuario;
import br.com.nexus.service.UsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService(new UsuarioDAO());
    }

    @POST
    @Path("/cadastro")
    public Response persistirUsuario(Usuario usuario) {
        try {
            usuarioService.persistirUsuario(usuario);
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response fazerLogin(UsuarioLoginDTO dto) {
        try {
            Usuario usuario = usuarioService.fazerLogin(dto);
            return Response.status(Response.Status.OK).entity(usuario).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarUsuarios(){
        try {
            List<Usuario> usuarios = usuarioService.pegarUsuarios();
            return Response.status(Response.Status.OK).entity(usuarios).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarUsuarioPorId(@PathParam("cpf") String cpf){
        try {
            Long idUsuario = usuarioService.pegarUsuarioPorId(cpf);
            return Response.status(Response.Status.OK).entity(idUsuario).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
