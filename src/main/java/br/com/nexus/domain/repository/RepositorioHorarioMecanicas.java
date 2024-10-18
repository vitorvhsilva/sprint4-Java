package br.com.nexus.domain.repository;

import br.com.nexus.domain.model.HorarioMecanica;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositorioHorarioMecanicas extends Repositorio{
    List<HorarioMecanica> pegarHorarioPorMecanica(Long idMecanica);
    LocalDateTime pegarHorarioPorId(Long idHorarioMecanica);
}
