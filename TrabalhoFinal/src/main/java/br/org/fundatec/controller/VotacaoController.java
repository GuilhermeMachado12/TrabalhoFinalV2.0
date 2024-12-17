package br.org.fundatec.controller;

import br.org.fundatec.exception.VotosException;
import br.org.fundatec.model.Funcionario;
import br.org.fundatec.model.Restaurante;
import br.org.fundatec.model.Voto;
import br.org.fundatec.model.VotoPorRestaurante;
import br.org.fundatec.repository.FuncionarioRepository;
import br.org.fundatec.repository.RestauranteRepository;
import br.org.fundatec.repository.VotoRepository;

import javax.persistence.PersistenceException;
import java.util.Calendar;
import java.util.List;

/**
 *
 */

public class VotacaoController {

    private FuncionarioRepository funcionarioRepository;
    private RestauranteRepository restauranteRepository;
    private VotoRepository votoRepository;

    public VotacaoController() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.restauranteRepository = new RestauranteRepository();
        this.votoRepository = new VotoRepository();
    }

    /**
     *
     * @param nome
     * @throws VotosException
     */

    public void adicionarRestaurante(String nome) throws VotosException {
        Restaurante restaurante = restauranteRepository.buscarPorNome(nome);

        if (restaurante == null) {
            restaurante = new Restaurante(nome);
            try {
                restauranteRepository.inserir(restaurante);
                System.out.println("Restaurante inserido com sucesso.");
            } catch (PersistenceException e) {
                throw new VotosException("Erro ao inserir o restaurante: ");
            }
        } else {
            System.out.println("Restaurante já cadastrado no sistema.");
        }
    }

    /**
     *
     * @param nome
     * @throws VotosException
     */

    public void adicionarFuncionario(String nome) throws VotosException {
        Funcionario funcionario = funcionarioRepository.buscarPorNome(nome);

        if (funcionario == null) {
            funcionario = new Funcionario(nome);
            try {
                funcionarioRepository.inserir(funcionario);
                System.out.println("Funcionário inserido com sucesso.");
            } catch (PersistenceException e) {
                throw new VotosException("Erro ao inserir o funcionário: ");
            }
        } else {
            System.out.println("Funcionário já cadastrado no sistema.");
        }
    }

    /**
     *
     * @return
     * @throws VotosException
     */

    public List<Funcionario> listarFuncionarios() throws VotosException {
        try {
            List<Funcionario> funcionarios = funcionarioRepository.listarTodos();
            if (funcionarios.isEmpty()) {
                throw new VotosException("Nenhum funcionário encontrado no banco de dados.");
            }
            return funcionarios;
        } catch (PersistenceException e) {
            throw new VotosException("Erro ao listar funcionários: ");
        }
    }

    /**
     *
     * @return
     * @throws VotosException
     */

    public List<Restaurante> listarRestaurante() throws VotosException {
        try {
            List<Restaurante> restaurantes = restauranteRepository.listarTodos();
            if (restaurantes.isEmpty()) {
                throw new VotosException("Nenhum funcionário encontrado no banco de dados.");
            }
            return restaurantes;
        } catch (PersistenceException e) {
            throw new VotosException("Erro ao listar funcionários: ");
        }
    }

    /**
     *
     * @param nomeFuncionario
     * @param idRestaurante
     * @param data
     * @throws VotosException
     */

    public void cadastrarVoto(String nomeFuncionario, Integer idRestaurante, Calendar data) throws VotosException {
        try {
            Funcionario funcionario = funcionarioRepository.buscarPorNome(nomeFuncionario);
            if (funcionario == null) {
                System.out.println("Funcionário não encontrado!");
                return;
            }

            Restaurante restaurante = restauranteRepository.buscarporId(idRestaurante);
            if (restaurante == null) {
                System.out.println("Restaurante não encontrado!");
                return;
            }

            List<Voto> votos = votoRepository.listarTodos();
            boolean votou = false;
            for (Voto voto : votos) {
                Calendar dataVoto = voto.getData();
                if (voto.getFuncionario().equals(funcionario) &&
                        dataVoto.get(Calendar.YEAR) == data.get(Calendar.YEAR) &&
                        dataVoto.get(Calendar.DAY_OF_YEAR) == data.get(Calendar.DAY_OF_YEAR)) {
                    votou = true;
                }
            }

            if (votou) {
                System.out.println("O funcionário já votou nessa data!");
            } else {
                Voto voto = new Voto(data, funcionario, restaurante);
                votoRepository.inserir(voto);
                System.out.println("Voto registrado com sucesso!");
            }
        } catch (PersistenceException e) {
            throw new VotosException("Erro ao registrar o voto: " );
        }
    }

    /**
     *
     * @return
     * @throws VotosException
     */

    public List<VotoPorRestaurante> apurarVotacao() throws VotosException {
        try {
            List<VotoPorRestaurante> resultado = votoRepository.apuracaoDeVotos();
            if (resultado.isEmpty()) {
                System.out.println("Nenhum voto registrado para a apuração.");
            }
            return resultado;
        } catch (PersistenceException e) {
            throw new VotosException("Erro ao apurar os votos: ");
        }
    }

    }








