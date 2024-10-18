package br.com.nexus.infra.dao;

import br.com.nexus.domain.model.Mecanica;
import br.com.nexus.domain.model.Orcamento;
import br.com.nexus.domain.repository.RepositorioMecanicas;
import br.com.nexus.domain.repository.RepositorioOrcamentos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MecanicaDAO implements RepositorioMecanicas {
    private Connection conexao;

    public MecanicaDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId(){
        Long id = null;
        try{
            String sql = "SELECT tb_mecanica_id_mecanica_seq.NEXTVAL FROM DUAL";
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

    public List<Mecanica> pegarMecanicasPorBairro(String bairro){
        String sqlSelect = """
            SELECT m.nome_mecanica
            FROM TB_MECANICA m INNER JOIN TB_ENDERECO_MECANICA e
            ON m.id_mecanica = e.id_mecanica
            WHERE e.bairro_endereco_mecanica = ?;
            """;
        List<Mecanica> mecanicas = new ArrayList<>();

        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, bairro);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                mecanicas.add(new Mecanica(rs.getString("nome_mecanica")));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mecanicas;
    }

    public void fecharConexao(){
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
