package br.com.nexus.service;

import br.com.nexus.domain.model.HorarioMecanica;
import br.com.nexus.domain.model.Mecanica;
import br.com.nexus.domain.repository.RepositorioHorarioMecanicas;
import br.com.nexus.domain.repository.RepositorioMecanicas;

import java.util.List;

public class MecanicaService {
    private RepositorioMecanicas repositorioMecanicas;
    private RepositorioHorarioMecanicas repositorioHorarioMecanicas;

    public MecanicaService(RepositorioMecanicas repositorioMecanicas, RepositorioHorarioMecanicas repositorioHorarioMecanicas) {
        this.repositorioMecanicas = repositorioMecanicas;
        this.repositorioHorarioMecanicas = repositorioHorarioMecanicas;
    }

    public List<HorarioMecanica> pegarHorariosPorMecanica(Long idMecanica) {
        List<HorarioMecanica> horarioMecanicas = repositorioHorarioMecanicas.pegarHorarioPorMecanica(idMecanica);
        fecharConexoes();
        return horarioMecanicas;
    }

    private void fecharConexoes(){
        repositorioHorarioMecanicas.fecharConexao();
        repositorioMecanicas.fecharConexao();
    }

    public List<Mecanica> pegarMecanicasPorBairro(String bairro) {
        List<Mecanica> mecanicas = repositorioMecanicas.pegarMecanicasPorBairro(bairro);
        fecharConexoes();
        return mecanicas;
    }
}
