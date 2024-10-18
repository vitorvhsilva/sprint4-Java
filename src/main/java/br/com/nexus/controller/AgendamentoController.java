package br.com.nexus.controller;

import br.com.nexus.domain.model.Agendamento;
import br.com.nexus.domain.model.DescricaoProblema;
import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.dto.AgendamentoInputDTO;
import br.com.nexus.dto.DescricaoProblemaInputDTO;
import br.com.nexus.infra.dao.AgendamentoDAO;
import br.com.nexus.infra.dao.DescricaoProblemaDAO;
import br.com.nexus.infra.dao.HorarioMecanicaDAO;
import br.com.nexus.infra.dao.VeiculoDAO;
import br.com.nexus.service.AgendamentoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("agendamentos")
public class AgendamentoController {
    private AgendamentoService agendamentoService;

    public AgendamentoController() {
        this.agendamentoService = new AgendamentoService(new AgendamentoDAO(), new HorarioMecanicaDAO());
    }

    @Path("/veiculo/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarAgendamentosPorVeiculo(@PathParam("id") Long idVeiculo){
        try {
            List<Agendamento> agendamentos = agendamentoService.pegarAgendamentosPorVeiculo(idVeiculo);
            return Response.status(Response.Status.OK).entity(agendamentos).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @Path("/mecanica/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarAgendamentosPorMecanica(@PathParam("id") Long idMecanica){
        try {
            List<Agendamento> agendamentos = agendamentoService.pegarAgendamentosPorMecanica(idMecanica);
            return Response.status(Response.Status.OK).entity(agendamentos).build();
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
    public Response persistirAgendamento(AgendamentoInputDTO dto){
        try {
            Agendamento agendamento = agendamentoService.persistirDescricao(dto);
            return Response.status(Response.Status.CREATED).entity(agendamento).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
