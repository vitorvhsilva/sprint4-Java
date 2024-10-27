package br.com.nexus.infra.dao;

import br.com.nexus.domain.model.Orcamento;
import br.com.nexus.domain.repository.RepositorioOrcamentos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAO implements RepositorioOrcamentos {
    private Connection conexao;

    public OrcamentoDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId(){
        Long id = null;
        try{
            String sql = "SELECT tb_orcamento_id_orcamento_seq.NEXTVAL FROM DUAL";
            PreparedStatement obterId = conexao.prepareStatement(sql);
            ResultSet rs = obterId.executeQuery();
            while (rs.next()){
                id = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public <T> void persistirDado(T t){
        Orcamento orcamento = (Orcamento) t;
        String sqlInsertTbOrcamento = """
                INSERT INTO TB_ORCAMENTO(id_orcamento, id_veiculo, id_diagnostico, valor_orcamento, data_orcamento)
                VALUES(?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement insercaoOrcamento = conexao.prepareStatement(sqlInsertTbOrcamento);
            insercaoOrcamento.setLong(1, obterProximoId());
            insercaoOrcamento.setLong(2, orcamento.getIdVeiculo());
            insercaoOrcamento.setLong(3, orcamento.getIdDiagnostico());
            insercaoOrcamento.setDouble(4, orcamento.getValorOrcamento());
            insercaoOrcamento.setTimestamp(5, Timestamp.valueOf(orcamento.getDataOrcamento()));

            insercaoOrcamento.execute();
            insercaoOrcamento.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Orcamento> pegarOrcamentos(long idVeiculo){
        String sqlSelect = "SELECT * FROM TB_ORCAMENTO WHERE id_veiculo = ?";
        List<Orcamento> orcamentos = new ArrayList<>();

        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idVeiculo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Orcamento orcamento = new Orcamento();
                orcamento.setIdVeiculo(idVeiculo);
                orcamento.setIdDiagnostico(rs.getLong("id_diagnostico"));
                orcamento.setValorOrcamento(rs.getDouble("valor_orcamento"));
                orcamento.setDataOrcamento(rs.getTimestamp("data_orcamento").toLocalDateTime());
                orcamentos.add(orcamento);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orcamentos;
    }

    public Long pegarOrcamentoPorDiagnostico(long idDiagnostico){
        String sqlSelect = "SELECT * FROM TB_ORCAMENTO WHERE id_diagnostico = ?";
        Long idOrcamento = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idDiagnostico);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                idOrcamento = rs.getLong("id_orcamento");
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idOrcamento;
    }

    public void fecharConexao(){
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
