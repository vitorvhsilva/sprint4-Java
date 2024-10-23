package br.com.nexus.service;

import br.com.nexus.domain.model.Diagnostico;
import br.com.nexus.domain.model.Veiculo;
import br.com.nexus.domain.repository.RepositorioDiagnosticos;
import br.com.nexus.domain.repository.RepositorioUsuarios;
import br.com.nexus.domain.repository.RepositorioVeiculos;
import br.com.nexus.dto.VeiculoDiagnosticoDTO;

import java.util.ArrayList;
import java.util.List;

public class VeiculoService {
    private RepositorioVeiculos repositorioVeiculos;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioDiagnosticos repositorioDiagnosticos;

    public VeiculoService(RepositorioVeiculos repositorioVeiculos, RepositorioUsuarios repositorioUsuarios, RepositorioDiagnosticos repositorioDiagnosticos) {
        this.repositorioVeiculos = repositorioVeiculos;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioDiagnosticos = repositorioDiagnosticos;
    }

    public List<Veiculo> pegarVeiculosDoUsuario(String cpf) {
        Long idUsuario = repositorioUsuarios.retornarIdPorCpf(cpf);
        List<Veiculo> veiculos = repositorioVeiculos.pegarVeiculos(idUsuario);
        fecharConexoes();
        return veiculos;
    }

    public void persistirVeiculo(Veiculo veiculo) {
        validarVeiculo(veiculo);
        repositorioVeiculos.persistirDado(veiculo);
        fecharConexoes();
    }


    public List<VeiculoDiagnosticoDTO> pegarVeiculosEDiagnosticoDoUsuario(String cpf) {
        Long idUsuario = repositorioUsuarios.retornarIdPorCpf(cpf);
        List<Veiculo> veiculos = repositorioVeiculos.pegarVeiculos(idUsuario);

        List<VeiculoDiagnosticoDTO> dtos = new ArrayList<>();

        for (Veiculo veiculo: veiculos) {
            List<Diagnostico> diagnosticos = repositorioDiagnosticos.pegarDiagnosticosPorPlaca(veiculo.getPlaca());
            VeiculoDiagnosticoDTO dto = new VeiculoDiagnosticoDTO();
            dto.setMarca(veiculo.getMarca());
            dto.setAno(veiculo.getAno());
            dto.setPlaca(veiculo.getPlaca());
            dto.setTipo(veiculo.getTipo());
            dto.setIdUsuario(veiculo.getIdUsuario());
            dto.setModelo(dto.getModelo());
            dto.setDiagnosticos(diagnosticos);
            dtos.add(dto);
        }
        return dtos;
    }

    private void fecharConexoes() {
        repositorioVeiculos.fecharConexao();
        repositorioUsuarios.fecharConexao();
        repositorioDiagnosticos.fecharConexao();
    }

    private void validarVeiculo(Veiculo veiculo) {
        if (veiculo.getMarca().length() < 3) {
            throw new RuntimeException("Marca inválida! Insira a marca novamente");
        }

        if (veiculo.getModelo().length() < 2) {
            throw new RuntimeException("Modelo inválido! Insira o modelo novamente");
        }

        if (!veiculo.getTipo().equalsIgnoreCase("C") && !veiculo.getTipo().equalsIgnoreCase("T")
                && !veiculo.getTipo().equalsIgnoreCase("M")) {
            throw new RuntimeException("Tipo inválido! Insira o tipo novamente \nC - Carro, M - Moto, T - Caminhão");
        }

        if (veiculo.getPlaca().length() != 7) {
            throw new RuntimeException("Placa inválida! Insira a placa novamente");
        }

        if (veiculo.getAno() < 1950 || veiculo.getAno() >  2024) {
            throw new RuntimeException("Ano inválido! Insira o ano novamente");
        }
    }
}
