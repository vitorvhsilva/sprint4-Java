package br.com.nexus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class HorarioMecanica {
    @JsonProperty
    private LocalDateTime horarioDisponivel;
    @JsonProperty
    private Long idMecanica;

    public HorarioMecanica() {
    }

    public HorarioMecanica(LocalDateTime horarioDisponivel, Long idMecanica) {
        this.horarioDisponivel = horarioDisponivel;
        this.idMecanica = idMecanica;
    }

    public LocalDateTime getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(LocalDateTime horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }

    public Long getIdMecanica() {
        return idMecanica;
    }

    public void setIdMecanica(Long idMecanica) {
        this.idMecanica = idMecanica;
    }
}
