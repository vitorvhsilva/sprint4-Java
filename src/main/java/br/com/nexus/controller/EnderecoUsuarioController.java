package br.com.nexus.controller;

import br.com.nexus.domain.model.EnderecoUsuario;
import br.com.nexus.dto.EnderecoUsuarioInputDTO;
import br.com.nexus.infra.dao.EnderecoUsuarioDAO;
import br.com.nexus.infra.dao.UsuarioDAO;
import br.com.nexus.service.EnderecoUsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("enderecos/usuarios")
public class EnderecoUsuarioController {
    private EnderecoUsuarioService enderecoUsuarioService;

    public EnderecoUsuarioController() {
        this.enderecoUsuarioService = new EnderecoUsuarioService(new EnderecoUsuarioDAO(), new UsuarioDAO());
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarEnderecosDoUsuario(@PathParam("id") Long idUsuario){
        try {
            List<EnderecoUsuario> enderecosUsuarios = enderecoUsuarioService.pegarEnderecosDoUsuario(idUsuario);
            return Response.status(Response.Status.OK).entity(enderecosUsuarios).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistirEndereco(EnderecoUsuarioInputDTO dto){
        try {
            enderecoUsuarioService.persistirEndereco(dto);
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
