package br.com.nexus.service;

import br.com.nexus.domain.model.DescricaoProblema;
import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.domain.repository.RepositorioDescricaoProblema;
import br.com.nexus.domain.repository.RepositorioVeiculos;
import br.com.nexus.dto.DescricaoProblemaInputDTO;
import br.com.nexus.dto.DiagnosticoInputDTO;
import br.com.nexus.dto.UsuarioeVeiculoOutputDTO;
import br.com.nexus.infra.dao.DiagnosticoDAO;

import java.time.LocalDateTime;
import java.util.List;

public class DescricaoProblemaService {
    private RepositorioDescricaoProblema repositorioDescricaoProblema;
    private RepositorioVeiculos repositorioVeiculos;
    private DiagnosticoService diagnosticoService;

    public DescricaoProblemaService(RepositorioDescricaoProblema repositorioDescricaoProblema, RepositorioVeiculos repositorioVeiculos) {
        this.repositorioDescricaoProblema = repositorioDescricaoProblema;
        this.repositorioVeiculos = repositorioVeiculos;
        this.diagnosticoService = new DiagnosticoService(new DiagnosticoDAO());
    }

    public List<DescricaoProblema> pegarDescricoesPorVeiculo(Long idVeiculo) {
        List<DescricaoProblema> descricoes = repositorioDescricaoProblema.pegarDescricoes(idVeiculo);
        fecharConexoes();
        return descricoes;
    }

    public Diagnostico persistirDescricao(DescricaoProblemaInputDTO dto) {
        // fazendo a descricao problema
        UsuarioeVeiculoOutputDTO usuarioeVeiculo = repositorioVeiculos.pegarUsuarioEVeiculoPelaPlaca(dto.getPlacaVeiculo());
        DescricaoProblema descricaoProblema = new DescricaoProblema(dto.getDescricao(), LocalDateTime.now(),
                usuarioeVeiculo.getIdUsuario(), usuarioeVeiculo.getIdVeiculo());
        repositorioDescricaoProblema.persistirDado(descricaoProblema);

        System.out.println(descricaoProblema);
        // fazendo o diagnostico
        Long idDescricaoProblema = repositorioDescricaoProblema.buscarIdPorVeiculoEDescricao(descricaoProblema);
        System.out.println(idDescricaoProblema);
        DiagnosticoInputDTO diagnosticoDTO = new DiagnosticoInputDTO("Diagnostico vindo de IA", usuarioeVeiculo.getIdVeiculo(), idDescricaoProblema);

        // criando o diagnostico pra retornar na response
        Diagnostico diagnostico = diagnosticoService.persistirDiagnostico(diagnosticoDTO);


        fecharConexoes();

        return diagnostico;
    }

    private void fecharConexoes() {
        repositorioDescricaoProblema.fecharConexao();
        repositorioVeiculos.fecharConexao();
    }
}
