package br.com.nexus.infra.dao;

import br.com.nexus.domain.repository.RepositorioUsuarios;
import br.com.nexus.domain.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements RepositorioUsuarios {
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
        return ;
    }

    public void persistirUsuario(Usuario usuario) {
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
            System.out.println("antes");
            statementUsuario.execute();
            System.out.println("depois");
            statementUsuario.close();
        }catch (SQLException e) {
            System.out.println("excecao");
            throw new RuntimeException(e);
        }
    }

    public boolean usuarioExistePorCpf(String cpf) {
        String sqlSelect = "SELECT * FROM TB_USUARIO WHERE cpf_usuario = ?";
        boolean existe = false;

        try (PreparedStatement statement = conexao.prepareStatement(sqlSelect)) {
            statement.setString(1, cpf);
            try (ResultSet rs = statement.executeQuery()) {
                existe = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return existe;
    }

    public boolean usuarioExistePorEmail(String email) {

        String sqlSelect = "SELECT * FROM TB_USUARIO WHERE email_usuario = ?";
        boolean existe = false;

        try (PreparedStatement statement = conexao.prepareStatement(sqlSelect)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                existe = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return existe;
    }

    public Long retornarIdPorCpf(String cpf) {

        String sqlSelect = "SELECT * FROM TB_USUARIO WHERE cpf_usuario = ?";
        Long id = null;
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, cpf);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getLong("id_usuario");
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public Usuario retornarUsuarioPorLogin(String email, String senha) {

        String sqlSelect = "SELECT * FROM TB_USUARIO WHERE email_usuario = ? AND senha_usuario = ?";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            statement.setString(1, email);
            statement.setString(2, senha);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                System.out.println("Login não encontrado!");
                return null;
            }

            usuario.setNome(rs.getString("nome_usuario"));
            usuario.setEmail(rs.getString("email_usuario"));
            usuario.setSenha(rs.getString("senha_usuario"));
            usuario.setGenero(rs.getString("genero_usuario"));
            usuario.setTelefone(rs.getString("telefone_usuario"));
            usuario.setCpf(rs.getString("cpf_usuario"));

            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    @Override
    public List<Usuario> retornarUsuarios() {
        String sqlSelect = "SELECT * FROM TB_USUARIO";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement statement = conexao.prepareStatement(sqlSelect);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setEmail(rs.getString("email_usuario"));
                usuario.setSenha(rs.getString("senha_usuario"));
                usuario.setGenero(rs.getString("genero_usuario"));
                usuario.setTelefone(rs.getString("telefone_usuario"));
                usuario.setCpf(rs.getString("cpf_usuario"));
                usuarios.add(usuario);
            }


            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuarios;
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
