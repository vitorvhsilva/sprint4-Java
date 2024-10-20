package br.com.nexus.service;

import br.com.nexus.dto.UsuarioLoginDTO;
import br.com.nexus.domain.repository.RepositorioUsuarios;
import br.com.nexus.domain.model.Usuario;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioService {

    private RepositorioUsuarios usuarioDAO;

    public UsuarioService(RepositorioUsuarios usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void persistirUsuario(Usuario usuario) {
        validarUsuario(usuario);
        System.out.println("Usuário validado: " + usuario);
        usuarioDAO.persistirUsuario(usuario);
        System.out.println("Persistido!");
        usuarioDAO.fecharConexao();
    }

    private boolean validarEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void validarUsuario(Usuario usuario) {
//        if (usuarioDAO.usuarioExistePorEmail(usuario.getEmail())) {
//            throw new RuntimeException("Usuário já existe pelo email!");
//        }
//
//        if (usuarioDAO.usuarioExistePorCpf(usuario.getCpf())) {
//            throw new RuntimeException("Usuário já existe pelo cpf!");
//        }

        if (usuario.getNome().length() < 3) {
            throw new RuntimeException("Nome muito pequeno! Digite novamente");
        }

        if (!validarEmail(usuario.getEmail())) {
            throw new RuntimeException("Email inválido! Digite novamente");
        }

        if (usuario.getSenha().length() < 8) {
            throw new RuntimeException("Senha inválida! Digite novamente");
        }

        if (!usuario.getGenero().equalsIgnoreCase("O") && !usuario.getGenero().equalsIgnoreCase("F")
                && !usuario.getGenero().equalsIgnoreCase("M")) {
            throw new RuntimeException("Gênero inválido! Digite novamente (M - Masculino, F - Feminino, O - Outros)");
        }

        if (usuario.getTelefone().length() < 11) {
            throw new RuntimeException("Telefone inválido! Digite novamente. (Exemplo: 11999999999)");
        }

        if (usuario.getCpf().length() != 11) {
            throw new RuntimeException("CPF inválido! Digite novamente. (Exemplo: 12345678999)");
        }
        System.out.println("Validado!");
    }

    public Usuario fazerLogin(UsuarioLoginDTO dto) {
        Usuario usuario = usuarioDAO.retornarUsuarioPorLogin(dto.getEmail(), dto.getSenha());
        usuarioDAO.fecharConexao();
        return usuario;
    }

    public List<Usuario> pegarUsuarios() {
        return usuarioDAO.retornarUsuarios();
    }
}
