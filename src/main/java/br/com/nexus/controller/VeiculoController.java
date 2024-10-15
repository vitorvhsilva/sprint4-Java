package br.com.nexus.controller;

import br.com.nexus.domain.model.Veiculo;
import br.com.nexus.infra.dao.UsuarioDAO;
import br.com.nexus.infra.dao.VeiculoDAO;
import br.com.nexus.service.VeiculoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("veiculos")
public class VeiculoController {
    private VeiculoService veiculoService;

    public VeiculoController() {
        this.veiculoService = new VeiculoService(new VeiculoDAO(), new UsuarioDAO());
    }

    @POST
    public Response persistirVeiculo(Veiculo veiculo){
        try {
            veiculoService.persistirVeiculo(veiculo);
            return Response.status(Response.Status.CREATED).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/usuario/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarUsuarios(@PathParam("cpf") String cpf){
        try {
            List<Veiculo> veiculos = veiculoService.pegarVeiculosDoUsuario(cpf);
            return Response.status(Response.Status.OK).entity(veiculos).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
