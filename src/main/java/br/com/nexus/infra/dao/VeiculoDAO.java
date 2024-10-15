package br.com.nexus.infra.dao;

import br.com.nexus.domain.model.Veiculo;
import br.com.nexus.domain.repository.Repositorio;
import br.com.nexus.domain.repository.RepositorioVeiculos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements RepositorioVeiculos {
    private Connection conexao;

    public VeiculoDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_veiculo_id_veiculo_seq.NEXTVAL FROM DUAL";
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
    public <T> void persistirDado(T t) {
        Veiculo veiculo = (Veiculo) t;
        String sqlInsertTbVeiculo = """
                INSERT INTO TB_VEICULO (id_veiculo, placa_veiculo, marca_veiculo, modelo_veiculo, tipo_veiculo, ano_veiculo, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement statementUsuario = conexao.prepareStatement(sqlInsertTbVeiculo);
            statementUsuario.setLong(1, obterProximoId());
            statementUsuario.setString(2, veiculo.getPlaca());
            statementUsuario.setString(3, veiculo.getMarca());
            statementUsuario.setString(4, veiculo.getModelo());
            statementUsuario.setString(5, veiculo.getTipo());
            statementUsuario.setInt(6, veiculo.getAno());
            statementUsuario.setLong(7, veiculo.getIdUsuario());
            statementUsuario.execute();
            statementUsuario.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Veiculo> pegarVeiculos(Long idUsuario) {
        String sqlSelect = "SELECT * FROM TB_VEICULO WHERE id_usuario = ?";
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idUsuario);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa_veiculo"));
                veiculo.setMarca(rs.getString("marca_veiculo"));
                veiculo.setModelo(rs.getString("modelo_veiculo"));
                veiculo.setAno(rs.getInt("ano_veiculo"));
                veiculo.setTipo(rs.getString("tipo_veiculo"));
                veiculo.setIdUsuario(idUsuario);
                veiculos.add(veiculo);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veiculos;
    }

    public Long pegarIdPelaPlaca(String placa) {
        String sqlSelect = "SELECT * FROM TB_VEICULO WHERE placa_veiculo = ?";
        Long idVeiculo = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, placa);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                idVeiculo = rs.getLong("id_veiculo");
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idVeiculo;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
