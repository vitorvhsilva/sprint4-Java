package br.com.nexus.infra.dao;

import br.com.nexus.domain.model.Agendamento;
import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.domain.repository.RepositorioAgendamentos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO implements RepositorioAgendamentos {
    private Connection conexao;

    public AgendamentoDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_agendamento_id_agendamento_seq.NEXTVAL FROM DUAL";
            PreparedStatement comandoDeGeracao = conexao.prepareStatement(sql);
            ResultSet rs = comandoDeGeracao.executeQuery();
            while(rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public <T> void persistirDado(T t){
        Agendamento agendamento = (Agendamento) t;
        String sqlInsert = """
                INSERT INTO TB_AGENDAMENTO (id_agendamento, id_veiculo, id_mecanica, id_orcamento, id_horario_mecanica, dia_data_agendamento)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement insercaoAgendamento = conexao.prepareStatement(sqlInsert);
            insercaoAgendamento.setLong(1, obterProximoId());
            insercaoAgendamento.setLong(2, agendamento.getIdVeiculo());
            insercaoAgendamento.setLong(3, agendamento.getIdMecanica());
            insercaoAgendamento.setLong(4, agendamento.getIdOrcamento());
            insercaoAgendamento.setLong(5, agendamento.getIdOrcamento());
            insercaoAgendamento.setTimestamp(6, Timestamp.valueOf(agendamento.getDiaDataAgendamento()));

            insercaoAgendamento.execute();
            insercaoAgendamento.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Agendamento> pegarAgendamentosPorVeiculo(long idVeiculo){
        String sqlSelect = "SELECT * FROM TB_AGENDAMENTO WHERE id_veiculo = ?";
        List<Agendamento> agendamentos = new ArrayList<>();
        try{
            PreparedStatement selectDiagnostico = conexao.prepareStatement(sqlSelect);
            selectDiagnostico.setLong(1, idVeiculo);
            ResultSet rs = selectDiagnostico.executeQuery();
            while(rs.next()){
                Agendamento agendamento = new Agendamento();
                agendamento.setIdVeiculo(rs.getLong("id_veiculo"));
                agendamento.setIdMecanica(rs.getLong("id_mecanica"));
                agendamento.setIdOrcamento(rs.getLong("id_orcamento"));
                agendamento.setIdHorarioMecanica(rs.getLong("id_horario_mecanica"));
                agendamento.setDiaDataAgendamento(rs.getTimestamp("dia_data_agendamento").toLocalDateTime());
                agendamentos.add(agendamento);
            }

            rs.close();
            selectDiagnostico.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return agendamentos;
    }

    public List<Agendamento> pegarAgendamentosPorMecanica(long idMecanica){
        String sqlSelect = "SELECT * FROM TB_AGENDAMENTO WHERE id_mecanica = ?";
        List<Agendamento> agendamentos = new ArrayList<>();
        try{
            PreparedStatement selectDiagnostico = conexao.prepareStatement(sqlSelect);
            selectDiagnostico.setLong(1, idMecanica);
            ResultSet rs = selectDiagnostico.executeQuery();
            while(rs.next()){
                Agendamento agendamento = new Agendamento();
                agendamento.setIdVeiculo(rs.getLong("id_veiculo"));
                agendamento.setIdMecanica(rs.getLong("id_mecanica"));
                agendamento.setIdOrcamento(rs.getLong("id_orcamento"));
                agendamento.setIdHorarioMecanica(rs.getLong("id_horario_mecanica"));
                agendamento.setDiaDataAgendamento(rs.getTimestamp("dia_data_agendamento").toLocalDateTime());
                agendamentos.add(agendamento);
            }

            rs.close();
            selectDiagnostico.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return agendamentos;
    }

    public void fecharConexao(){
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
