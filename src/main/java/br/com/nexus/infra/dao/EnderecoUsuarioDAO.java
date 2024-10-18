package br.com.nexus.infra.dao;

import br.com.nexus.domain.model.EnderecoUsuario;
import br.com.nexus.domain.repository.RepositorioEnderecoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoUsuarioDAO implements RepositorioEnderecoUsuario {
    private Connection conexao;

    public EnderecoUsuarioDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId(){
        return null;
    }

    @Override
    public <T> void persistirDado(T t){
        EnderecoUsuario enderecoUsuario = (EnderecoUsuario) t;
        String sqlInsertTbEnderecoUsuario = """
                INSERT INTO TB_ENDERECO_USUARIO (id_usuario, num_endereco_usuario, rua_endereco_usuario, cep_endereco_usuario, bairro_endereco_usuario)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement insercaoEndereco = conexao.prepareStatement(sqlInsertTbEnderecoUsuario);
            insercaoEndereco.setLong(1, enderecoUsuario.getIdUsuario());
            insercaoEndereco.setString(2, enderecoUsuario.getNumEnderecoUsuario());
            insercaoEndereco.setString(3, enderecoUsuario.getRuaEnderecoUsuario());
            insercaoEndereco.setString(4, enderecoUsuario.getCepEnderecoUsuario());
            insercaoEndereco.setString(5, enderecoUsuario.getBairroEnderecoUsuario());
            insercaoEndereco.execute();
            insercaoEndereco.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EnderecoUsuario> pegarEnderecosPorUsuario(Long idUsuario){
        String sqlSelect = """
            SELECT *
            FROM TB_ENDERECO_USUARIO
            WHERE id_usuario = ?
            """;
        List<EnderecoUsuario> enderecosUsuarios = new ArrayList<>();

        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setLong(1, idUsuario);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                EnderecoUsuario enderecoUsuario = new EnderecoUsuario();
                enderecoUsuario.setIdUsuario(idUsuario);
                enderecoUsuario.setRuaEnderecoUsuario(rs.getString("rua_endereco_usuario"));
                enderecoUsuario.setNumEnderecoUsuario(rs.getString("num_endereco_usuario"));
                enderecoUsuario.setCepEnderecoUsuario(rs.getString("cep_endereco_usuario"));
                enderecoUsuario.setBairroEnderecoUsuario(rs.getString("bairro_endereco_usuario"));
                enderecosUsuarios.add(enderecoUsuario);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enderecosUsuarios;
    }

    public void fecharConexao(){
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
