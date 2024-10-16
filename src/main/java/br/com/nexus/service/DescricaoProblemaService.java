package br.com.nexus.service;

import br.com.nexus.domain.model.DescricaoProblema;
import br.com.nexus.domain.repository.RepositorioDescricaoProblema;
import br.com.nexus.domain.repository.RepositorioVeiculos;
import br.com.nexus.dto.DescricaoProblemaInputDTO;
import br.com.nexus.dto.UsuarioeVeiculoOutputDTO;

import java.time.LocalDateTime;
import java.util.List;

public class DescricaoProblemaService {
    private RepositorioDescricaoProblema repositorioDescricaoProblema;
    private RepositorioVeiculos repositorioVeiculos;

    public DescricaoProblemaService(RepositorioDescricaoProblema repositorioDescricaoProblema, RepositorioVeiculos repositorioVeiculos) {
        this.repositorioDescricaoProblema = repositorioDescricaoProblema;
        this.repositorioVeiculos = repositorioVeiculos;
    }

    public List<DescricaoProblema> pegarDescricoesPorVeiculo(Long idVeiculo) {
        List<DescricaoProblema> descricoes = repositorioDescricaoProblema.pegarDescricoes(idVeiculo);
        repositorioDescricaoProblema.fecharConexao();
        return descricoes;
    }

    public void persistirDescricao(DescricaoProblemaInputDTO dto) {
        UsuarioeVeiculoOutputDTO usuarioeVeiculo = repositorioVeiculos.pegarUsuarioEVeiculoPelaPlaca(dto.getPlacaVeiculo());
        DescricaoProblema descricaoProblema = new DescricaoProblema(dto.getDescricao(), LocalDateTime.now(),
                usuarioeVeiculo.getIdUsuario(), usuarioeVeiculo.getIdVeiculo());
        repositorioDescricaoProblema.persistirDado(descricaoProblema);
        repositorioDescricaoProblema.fecharConexao();
    }
}
