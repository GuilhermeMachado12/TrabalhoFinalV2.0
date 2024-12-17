package br.org.fundatec.repository;

import javax.persistence.*;

import br.org.fundatec.model.Restaurante;
import java.util.List;

/**
 *  Classe RestauranteRepository criada pelo
 * @author Guilherme.
 *  É a classe responsável pelo acesso aos dados da entidade {@code Restaurante} no banco de dados.
 *  Para realizar operações de inserção, remoção, atualização e consulta na tabela
 *  associada à entidade {@code Restaurante}.
 */
public class RestauranteRepository {

    /**
     * É o gerenciador de entidades responsável pela comunicação com o banco de dados.
     */
    private EntityManager em;

    /**
     * Construtor padrão que inicializa o {@link EntityManager} utilizando a {@link EntityManagerFactory}.
     * A unidade de persistência configurada é "TFinalDB".
     */
    public RestauranteRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TFinalDB");
        em = factory.createEntityManager();
    }

    /**
     * Inseri um novo restaurante no banco de dados.
     * @param restaurante
     * o metodo recebe o restaurante por parâmetro para poder fazer uma busca ao banco de dados
     * e assim poderá inserir um novo restaurante que já não tenha sido inserido.
     */
    public void inserir(Restaurante restaurante){
        em.getTransaction().begin();
        em.merge(restaurante);
        em.getTransaction().commit();
    }

    /**
     * Remove um restaurante do banco de dados.
     * @param restaurante
     * o metodo recebe o restaurante por parâmetro para poder fazer uma busca no banco de dados
     * e ao encontrar o restaurante solicitado já inserido poderá removê-lo do banco de dados.
     */
    public void remover(Restaurante restaurante){
        em.getTransaction().begin();
        em.remove(restaurante);
        em.getTransaction().commit();
    }

    /**
     *Atualiza os dados de um restaurante existente no banco de dados.
     * @param restaurante
     * O metodo recebe o restaurante por parâmetro para poder fazer uma busca no banco de dados
     * e ao encontrar o restaurante solicitado já inserido poderá atualizar os seus dados.
     */
    public void atualizar(Restaurante restaurante){
        em.getTransaction().begin();
        em.merge(restaurante);
        em.getTransaction().commit();
    }

    /**
     * Lista todos os restaurante cadastrados no banco de dados.
     * @return Retorna uma lista contendo todos os restaurante.
     */
    public List<Restaurante> listarTodos(){
        TypedQuery<Restaurante> listarTodos = this.em.createQuery("select res from Restaurante res",Restaurante.class);
        return listarTodos.getResultList();
    }

    /**
     * Busca o restaurante pelo seu identificador único(id).
     * @param id O metodo recebe por parâmetro o id no qual é o
     * identificador do restaurante a ser buscado.
     * @return Retorna o restaurante correspondente ao id informado.
     */
    public Restaurante buscarporId(Integer id) {
        return  this.em.find(Restaurante.class, id);
    }

    /**
     * Busca o restaurante pelo seu nome.
     * @param nome O metodo recebe por parâmetro o nome no qual é o
     * nome do restaurante a ser buscado.
     * @return Retorna o restaurante correspondente ao nome informado.
     */
    public Restaurante buscarPorNome(String nome) {
            TypedQuery<Restaurante> query = this.em.createQuery("select r from Restaurante r where r.nome like :nome", Restaurante.class);
            query.setParameter("nome", nome);

            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }

        }


}



