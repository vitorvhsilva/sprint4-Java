package br.com.nexus.infra.dao;

import br.com.nexus.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection conexao;

    public UsuarioDAO() {
        this.conexao = new ConnectionFactory().obterConexao();
    }

    public void persistirUsuario(Usuario usuario) {
        String sqlInsert = """
                INSERT INTO TB_USUARIO (nome_usuario, email_usuario, senha_usuario, genero_usuario, telefone_usuario, cpf_usuario)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlInsert);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getGenero());
            ps.setString(5, usuario.getTelefone());
            ps.setString(6, usuario.getCpf());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void fechar() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
