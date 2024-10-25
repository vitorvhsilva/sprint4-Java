package br.com.nexus.infra.dao;

import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.domain.repository.RepositorioDiagnosticos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoDAO implements RepositorioDiagnosticos {
    private Connection conexao;

    public DiagnosticoDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_diagnostico_id_diagnostico_seq.NEXTVAL FROM DUAL";
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
        Diagnostico diagnostico = (Diagnostico) t;
        String sqlInsert = """
                INSERT INTO TB_DIAGNOSTICO(id_diagnostico, diagnostico_veiculo, data_diagnostico, id_veiculo, id_descricao_problema) VALUES(?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement insercaoDiagnostico = conexao.prepareStatement(sqlInsert);
            insercaoDiagnostico.setLong(1, obterProximoId());
            insercaoDiagnostico.setString(2, diagnostico.getDiagnosticoVeiculo());
            insercaoDiagnostico.setTimestamp(3, Timestamp.valueOf(diagnostico.getDataDiagnostico()));
            insercaoDiagnostico.setLong(4, diagnostico.getIdVeiculo());
            insercaoDiagnostico.setLong(5, diagnostico.getIdDescricaoProblema());

            insercaoDiagnostico.execute();
            insercaoDiagnostico.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarDiagnostico(Long idDiagnostico) {
        String sqlUpdate = "UPDATE TB_DIAGNOSTICO SET feito_diagnostico = 1 WHERE id_diagnostico = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sqlUpdate);
            preparedStatement.setLong(1, idDiagnostico);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Diagnostico> pegarDiagnosticos(long idVeiculo){
        String sqlSelect = "SELECT * FROM TB_DIAGNOSTICO WHERE id_veiculo = ?";
        List<Diagnostico> diagnosticos = new ArrayList<>();
        try{
            PreparedStatement selectDiagnostico = conexao.prepareStatement(sqlSelect);
            selectDiagnostico.setLong(1, idVeiculo);
            ResultSet rs = selectDiagnostico.executeQuery();
            while(rs.next()){
                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setDataDiagnostico(rs.getTimestamp("data_diagnostico").toLocalDateTime());
                diagnostico.setDiagnosticoVeiculo(rs.getString("diagnostico_veiculo"));
                diagnostico.setIdVeiculo(idVeiculo);
                diagnostico.setFeitoDiagnostico(rs.getInt("feito_diagnostico"));
                diagnostico.setIdDescricaoProblema(rs.getLong("id_descricao_problema"));
                diagnosticos.add(diagnostico);
            }

            rs.close();
            selectDiagnostico.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return diagnosticos;
    }

    public List<Diagnostico> pegarDiagnosticosPorPlaca(String placa){
        String sqlSelect = """
            SELECT d.* FROM TB_VEICULO v
            INNER JOIN TB_DIAGNOSTICO d
            ON v.id_veiculo = d.id_veiculo
            WHERE v.placa_veiculo = ?
            """;
        List<Diagnostico> diagnosticos = new ArrayList<>();
        try{
            PreparedStatement selectDiagnostico = conexao.prepareStatement(sqlSelect);
            selectDiagnostico.setString(1, placa);
            ResultSet rs = selectDiagnostico.executeQuery();
            while(rs.next()){
                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setDataDiagnostico(rs.getTimestamp("data_diagnostico").toLocalDateTime());
                diagnostico.setDiagnosticoVeiculo(rs.getString("diagnostico_veiculo"));
                diagnostico.setIdVeiculo(rs.getLong("id_veiculo"));
                diagnostico.setFeitoDiagnostico(rs.getInt("feito_diagnostico"));
                diagnostico.setIdDescricaoProblema(rs.getLong("id_descricao_problema"));
                diagnosticos.add(diagnostico);
            }

            rs.close();
            selectDiagnostico.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return diagnosticos;
    }

    @Override
    public void atualizarDiagnostico(Diagnostico diagnostico) {
        String sqlUpdate = """
                UPDATE TB_DIAGNOSTICO 
                SET feito_diagnostico = 1 
                WHERE id_descricao_problema = ? AND id_veiculo = ? AND diagnostico_veiculo = ? AND data_diagnostico = ?
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlUpdate);
            ps.setLong(1, diagnostico.getIdDescricaoProblema());
            ps.setLong(2, diagnostico.getIdVeiculo());
            ps.setString(3, diagnostico.getDiagnosticoVeiculo());
            ps.setTimestamp(4, Timestamp.valueOf(diagnostico.getDataDiagnostico()));
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Long buscarIdPorDataEIds(Diagnostico diagnostico) {
        String sqlSelect = "SELECT * FROM TB_DIAGNOSTICO WHERE data_diagnostico= ? AND id_veiculo = ? AND id_descricao_problema = ?";
        Long idDiagnostico = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setTimestamp(1, Timestamp.valueOf(diagnostico.getDataDiagnostico()));
            statement.setLong(2, diagnostico.getIdVeiculo());
            statement.setLong(3, diagnostico.getIdDescricaoProblema());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                idDiagnostico = rs.getLong("id_diagnostico");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idDiagnostico;
    }

    public void fecharConexao(){
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
