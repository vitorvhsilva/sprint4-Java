package br.com.nexus.controller;

import br.com.nexus.domain.model.HorarioMecanica;
import br.com.nexus.domain.model.Mecanica;
import br.com.nexus.dto.MecanicaBairroInputDTO;
import br.com.nexus.infra.dao.HorarioMecanicaDAO;
import br.com.nexus.infra.dao.MecanicaDAO;
import br.com.nexus.service.MecanicaService;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("mecanicas")
public class MecanicaController {
    private MecanicaService mecanicaService;

    public MecanicaController() {
        this.mecanicaService = new MecanicaService(new MecanicaDAO(), new HorarioMecanicaDAO());
    }

    @Path("/horarios/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarHorariosPorMecanica(@PathParam("id") Long idMecanica){
        try {
            List<HorarioMecanica> horarioMecanicas = mecanicaService.pegarHorariosPorMecanica(idMecanica);
            return Response.status(Response.Status.OK).entity(horarioMecanicas).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/bairro")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pegarMecanicasPorBairro(MecanicaBairroInputDTO dto){
        try {
            List<Mecanica> mecanicas = mecanicaService.pegarMecanicasPorBairro(dto.getBairro());
            return Response.status(Response.Status.OK).entity(mecanicas).build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
