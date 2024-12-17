package br.org.fundatec.repository;

import javax.persistence.*;

import br.org.fundatec.model.Funcionario;
import br.org.fundatec.model.Restaurante;

import java.util.List;

/**
 *  Classe FuncionarioRepository criada pelo
 * @author Guilherme.
 *  É a classe responsável pelo acesso aos dados da entidade {@code Funcionario} no banco de dados.
 *  Para realizar operações de inserção, remoção, atualização e consulta na tabela
 *  associada à entidade {@code Funcionario}.
 */
public class FuncionarioRepository {

    /**
     * É o gerenciador de entidades responsável pela comunicação com o banco de dados.
     */
    private EntityManager em;

    /**
     * Construtor padrão que inicializa o {@link EntityManager} utilizando a {@link EntityManagerFactory}.
     * A unidade de persistência configurada é "TFinalDB".
     */
    public FuncionarioRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TFinalDB");
        em = factory.createEntityManager();
    }

    /**
     * Inseri um novo funcionário no banco de dados.
     * @param funcionario
     * o metodo recebe o funcionário por parâmetro para poder fazer uma busca ao banco de dados
     * e assim poderá inserir um novo funcionário que já não tenha sido inserido.
     */
    public void inserir(Funcionario funcionario) {
        em.getTransaction().begin();
        em.merge(funcionario);
        em.getTransaction().commit();
    }

    /**
     * Remove um funcionário do banco de dados.
     * @param funcionario
     * o metodo recebe o funcionário por parâmetro para poder fazer uma busca no banco de dados
     * e ao encontrar o funcionário solicitado já inserido poderá removê-lo do banco de dados.
     */
    public void remover(Funcionario funcionario) {
        em.getTransaction().begin();
        em.remove(funcionario);
        em.getTransaction().commit();
    }

    /**
     *Atualiza os dados de um funcionário existente no banco de dados.
     * @param funcionario
     * O metodo recebe o funcionário por parâmetro para poder fazer uma busca no banco de dados
     * e ao encontrar o funcionário solicitado já inserido poderá atualizar os seus dados.
     */
    public void atualizar(Funcionario funcionario) {
        em.getTransaction().begin();
        em.merge(funcionario);
        em.getTransaction().commit();
    }

    /**
     * Lista todos os funcionários cadastrados no banco de dados.
     * @return Retorna uma lista contendo todos os funcionários
     */
    public List<Funcionario> listarTodos() {
        TypedQuery<Funcionario> listarTodos = this.em.createQuery("select fun from Funcionario fun", Funcionario.class);
        return listarTodos.getResultList();
    }

    /**
     * Busca o funcionário pelo seu identificador único(id).
     * @param id O metodo recebe por parâmetro o id no qual é o
     * identificador do funcionário a ser buscado.
     * @return Retorna o funcionário correspondente ao id informado.
     */
    public Funcionario buscarporId(Integer id) {
        return this.em.find(Funcionario.class, id);
    }

    /**
     * Busca o funcionario pelo seu nome.
     * @param nome O metodo recebe por parâmetro o nome no qual é o
     * nome do funcionário a ser buscado.
     * @return Retorna o funcionário correspondente ao nome informado.
     */
    public Funcionario buscarPorNome(String nome) {
        TypedQuery<Funcionario> query = this.em.createQuery("select f from Funcionario f where f.nome like :nome", Funcionario.class);
        query.setParameter("nome", nome);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
