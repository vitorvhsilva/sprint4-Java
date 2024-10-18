package br.com.nexus.infra.dao;

import br.com.nexus.domain.model.HorarioMecanica;
import br.com.nexus.domain.model.Mecanica;
import br.com.nexus.domain.repository.RepositorioHorarioMecanicas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioMecanicaDAO implements RepositorioHorarioMecanicas {
    private Connection conexao;

    public HorarioMecanicaDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId(){
        return null;
    }

    @Override
    public <T> void persistirDado(T t){
        Mecanica mecanica = (Mecanica) t;
        String sqlInsertTbMecanica = """
                INSERT INTO TB_MECANICA (id_mecanica, nome_mecanica)
                VALUES (?, ?);
                """;

        try {
            PreparedStatement insercaoMecanica = conexao.prepareStatement(sqlInsertTbMecanica);
            insercaoMecanica.setLong(1, obterProximoId());
            insercaoMecanica.setString(2, mecanica.getNomeMecanica());
            insercaoMecanica.execute();
            insercaoMecanica.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HorarioMecanica> pegarHorarioPorMecanica(Long idMecanica){
        String sqlSelect = """
            SELECT *
            FROM TB_HORARIO_MECANICA
            WHERE id_mecanica = ?
            """;
        List<HorarioMecanica> horarioMecanicas = new ArrayList<>();

        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idMecanica);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                HorarioMecanica horarioMecanica = new HorarioMecanica();
                horarioMecanica.setHorarioDisponivel(rs.getTimestamp("horario_disponivel").toLocalDateTime());
                horarioMecanica.setIdMecanica(idMecanica);
                horarioMecanicas.add(horarioMecanica);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return horarioMecanicas;
    }

    public LocalDateTime pegarHorarioPorId(Long idHorarioMecanica){
        String sqlSelect = """
            SELECT *
            FROM TB_HORARIO_MECANICA
            WHERE id_horario_mecanica = ?
            """;
        List<HorarioMecanica> horarioMecanicas = new ArrayList<>();

        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idHorarioMecanica);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                HorarioMecanica horarioMecanica = new HorarioMecanica();
                horarioMecanica.setHorarioDisponivel(rs.getTimestamp("horario_disponivel").toLocalDateTime());
                horarioMecanica.setIdMecanica(rs.getLong("id_mecanica"));
                horarioMecanicas.add(horarioMecanica);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return horarioMecanicas.get(0).getHorarioDisponivel();
    }

    public void fecharConexao(){
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
