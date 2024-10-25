package br.com.nexus.service;

import br.com.nexus.domain.model.EnderecoUsuario;
import br.com.nexus.domain.repository.RepositorioEnderecoUsuario;
import br.com.nexus.domain.repository.RepositorioUsuarios;
import br.com.nexus.dto.EnderecoUsuarioInputDTO;

import java.util.List;

public class EnderecoUsuarioService {
    private RepositorioEnderecoUsuario repositorioEnderecoUsuario;
    private RepositorioUsuarios repositorioUsuarios;

    public EnderecoUsuarioService(RepositorioEnderecoUsuario repositorioEnderecoUsuario, RepositorioUsuarios repositorioUsuarios) {
        this.repositorioEnderecoUsuario = repositorioEnderecoUsuario;
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public void persistirEndereco(EnderecoUsuarioInputDTO dto) {
        Long idUsuario = repositorioUsuarios.retornarIdPorCpf(dto.getCpfUsuario());
        EnderecoUsuario enderecoUsuario = new EnderecoUsuario(idUsuario, dto.getNumEnderecoUsuario(), dto.getRuaEnderecoUsuario(),
                dto.getCepEnderecoUsuario(), dto.getBairroEnderecoUsuario());
        repositorioEnderecoUsuario.persistirDado(enderecoUsuario);
        fecharConexoes();
    }

    public List<EnderecoUsuario> pegarEnderecosDoUsuario(String cpf) {
        Long idUsuario = repositorioUsuarios.retornarIdPorCpf(cpf);
        List<EnderecoUsuario> enderecosUsuarios = repositorioEnderecoUsuario.pegarEnderecosPorUsuario(idUsuario);
        fecharConexoes();
        return enderecosUsuarios;
    }

    private void fecharConexoes() {
        repositorioUsuarios.fecharConexao();
        repositorioEnderecoUsuario.fecharConexao();
    }
}
