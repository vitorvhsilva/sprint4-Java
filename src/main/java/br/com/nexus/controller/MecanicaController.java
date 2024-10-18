package br.com.nexus.controller;

import br.com.nexus.domain.model.EnderecoUsuario;
import br.com.nexus.domain.model.HorarioMecanica;
import br.com.nexus.dto.EnderecoUsuarioInputDTO;
import br.com.nexus.infra.dao.EnderecoUsuarioDAO;
import br.com.nexus.infra.dao.HorarioMecanicaDAO;
import br.com.nexus.infra.dao.MecanicaDAO;
import br.com.nexus.infra.dao.UsuarioDAO;
import br.com.nexus.service.EnderecoUsuarioService;
import br.com.nexus.service.MecanicaService;

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
}
