package br.com.nexus.infra.dao;

import br.com.nexus.model.Repositorio;
import br.com.nexus.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements Repositorio {
    private Connection conexao;

    public UsuarioDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public Long obterProximoId() {
        Long id = null;
        try {
            String sql = "SELECT tb_usuario_id_usuario_seq.NEXTVAL FROM DUAL";
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
        Usuario usuario = (Usuario) t;
        String sqlInsertTbUsuario = """
                INSERT INTO TB_USUARIO (id_usuario, nome_usuario, email_usuario, senha_usuario, genero_usuario, telefone_usuario, cpf_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement statementUsuario = conexao.prepareStatement(sqlInsertTbUsuario);
            statementUsuario.setLong(1, obterProximoId());
            statementUsuario.setString(2, usuario.getNome());
            statementUsuario.setString(3, usuario.getEmail());
            statementUsuario.setString(4, usuario.getSenha());
            statementUsuario.setString(5, usuario.getGenero());
            statementUsuario.setString(6, usuario.getTelefone());
            statementUsuario.setString(7, usuario.getCpf());
            statementUsuario.execute();
            statementUsuario.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
