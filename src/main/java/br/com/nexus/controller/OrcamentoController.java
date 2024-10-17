package br.com.nexus.controller;

import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.domain.model.Orcamento;
import br.com.nexus.infra.dao.OrcamentoDAO;
import br.com.nexus.service.OrcamentoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("orcamentos")
public class OrcamentoController {
    private OrcamentoService orcamentoService;

    public OrcamentoController() {
        this.orcamentoService = new OrcamentoService(new OrcamentoDAO());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistirOrcamento(Diagnostico diagnostico){
        try {
            Orcamento orcamento = orcamentoService.persistirOrcamento(diagnostico);
            return Response.status(Response.Status.CREATED).entity(orcamento).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @Path("/veiculo/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarOrcamentosPorVeiculo(@PathParam("id") Long idVeiculo){
        try {
            List<Orcamento> orcamentos = orcamentoService.pegarOrcamentosPorVeiculo(idVeiculo);
            return Response.status(Response.Status.OK).entity(orcamentos).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
