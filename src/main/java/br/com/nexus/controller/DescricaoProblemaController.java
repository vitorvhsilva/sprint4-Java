package br.com.nexus.controller;

import br.com.nexus.domain.model.DescricaoProblema;
import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.dto.DescricaoProblemaInputDTO;
import br.com.nexus.infra.dao.DescricaoProblemaDAO;
import br.com.nexus.infra.dao.VeiculoDAO;
import br.com.nexus.service.DescricaoProblemaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("descricoes")
public class DescricaoProblemaController {
    private DescricaoProblemaService descricaoProblemaService;

    public DescricaoProblemaController() {
        this.descricaoProblemaService = new DescricaoProblemaService(new DescricaoProblemaDAO(), new VeiculoDAO());
    }

    @Path("/veiculo/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarDescricoesPorVeiculo(@PathParam("id") Long idVeiculo){
        try {
            List<DescricaoProblema> descricoes = descricaoProblemaService.pegarDescricoesPorVeiculo(idVeiculo);
            return Response.status(Response.Status.OK).entity(descricoes).build();
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
    public Response persistirDescricao(DescricaoProblemaInputDTO dto){
        try {
            Diagnostico diagnostico = descricaoProblemaService.persistirDescricao(dto);
            return Response.status(Response.Status.CREATED).entity(diagnostico).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
