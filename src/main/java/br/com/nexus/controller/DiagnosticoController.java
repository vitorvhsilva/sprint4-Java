package br.com.nexus.controller;

import br.com.nexus.domain.model.DescricaoProblema;
import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.dto.DiagnosticoInputDTO;
import br.com.nexus.infra.dao.DiagnosticoDAO;
import br.com.nexus.service.DiagnosticoService;
import br.com.nexus.service.ServicosDoDiagnostico;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("diagnosticos")
public class DiagnosticoController {
    private ServicosDoDiagnostico diagnosticoService;

    public DiagnosticoController() {
        this.diagnosticoService = new DiagnosticoService(new DiagnosticoDAO());
    }

    @POST
    public Response persistirDiagnostico(DiagnosticoInputDTO dto) {
        try {
            diagnosticoService.persistirDiagnostico(dto);
            return Response.status(Response.Status.CREATED).build();
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
    public Response pegarDiagnosticosPorVeiculo(@PathParam("id") Long idVeiculo){
        try {
            List<Diagnostico> diagnosticos = diagnosticoService.pegarDiagnosticosPorVeiculo(idVeiculo);
            return Response.status(Response.Status.OK).entity(diagnosticos).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

}
