package br.org.fundatec.repository;

import javax.persistence.*;

import br.org.fundatec.exception.VotosException;
import br.org.fundatec.model.Funcionario;
import br.org.fundatec.model.Restaurante;
import br.org.fundatec.model.Voto;
import br.org.fundatec.model.VotoPorRestaurante;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 *  Classe VotoRepository criada pelo
 * @author Guilherme.
 *  É a classe responsável pelo acesso aos dados da entidade {@code Voto} no banco de dados.
 *  Para realizar operações de inserção, remoção, atualização e consulta na tabela
 *  associada à entidade {@code Voto}.
 */
public class VotoRepository {

    /**
     * É o gerenciador de entidades responsável pela comunicação com o banco de dados.
     */
    private EntityManager em;

    /**
     * Construtor padrão que inicializa o {@link EntityManager} utilizando a {@link EntityManagerFactory}.
     * A unidade de persistência configurada é "TFinalDB".
     */
    public VotoRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TFinalDB");
        em = factory.createEntityManager();
    }

    /**
     * Inseri um novo voto no banco de dados.
     * @param voto
     * o metodo recebe o voto por parâmetro para poder fazer uma busca ao banco de dados
     * e assim poderá inserir um novo voto que já não tenha sido inserido.
     */
    public void inserir(Voto voto) {
        this.em.getTransaction().begin();
        this.em.merge(voto);
        this.em.getTransaction().commit();
    }

    /**
     * Remove um voto do banco de dados.
     * @param voto
     * o metodo recebe o voto por parâmetro para poder fazer uma busca no banco de dados
     * e ao encontrar o voto solicitado já inserido poderá removê-lo do banco de dados.
     */
    public void remover(Voto voto) {
        em.getTransaction().begin();
        em.remove(voto);
        em.getTransaction().commit();
    }

    /**
     *Atualiza os dados de um voto existente no banco de dados.
     * @param voto
     * O metodo recebe o voto por parâmetro para poder fazer uma busca no banco de dados
     * e ao encontrar o voto solicitado já inserido poderá atualizar os seus dados.
     */
    public void atualizar(Voto voto) {
        em.getTransaction().begin();
        em.merge(voto);
        em.getTransaction().commit();
    }

    /**
     * Lista todos os votos cadastrados no banco de dados.
     * @return Retorna uma lista contendo todos os votos.
     */
    public List<Voto> listarTodos() {
        TypedQuery<Voto> buscarTodos = this.em.createQuery("select vot from Voto vot", Voto.class);
        return buscarTodos.getResultList();
    }

    /**
     * Busca o voto pelo seu identificador único(id).
     * @param id O metodo recebe por parâmetro o id no qual é o
     * identificador do voto a ser buscado.
     * @return Retorna o votocorrespondente ao id informado.
     */
    public List<Voto> buscarPorId(Long id) {
        Voto voto = em.find(Voto.class, id);
        return voto != null ? Collections.singletonList(voto) : Collections.emptyList();
    }

    /**
     * Realiza a apuração dos votos para os restaurantes na data atual.
     * @return Retorna uma lista de votos contendo o nome do restaurante e a quantidade de votos recebido.
     * @throws VotosException Se não houver votos na data de hoje ou ocorrer um erro de persistência.
     */
    public List<VotoPorRestaurante> apuracaoDeVotos() throws VotosException {
        try {
            Calendar hj = Calendar.getInstance();
            hj.set(Calendar.HOUR_OF_DAY, 0);
            hj.set(Calendar.MINUTE, 0);
            hj.set(Calendar.SECOND, 0);
            hj.set(Calendar.MILLISECOND, 0);

            String query =
                    "select new br.org.fundatec.model.VotoPorRestaurante(r.nome, COUNT(r.id)) " +
                            " from Voto v " +
                            " inner join v.restaurante r" +
                            " where v.data = :hj" +
                            " group by r.nome order by r.nome";

            TypedQuery<VotoPorRestaurante> VotoPorRestaurante = this.em.createQuery(query, VotoPorRestaurante.class);
            VotoPorRestaurante.setParameter("hj", hj);

            List<VotoPorRestaurante> resultado = VotoPorRestaurante.getResultList();
            if (resultado.isEmpty()) {
                throw new VotosException("Nenhum voto encontrado para a data de hoje.");
            }
            return resultado;
        } catch (PersistenceException e) {
            throw new VotosException("Erro ao apurar os votos: ");
        }
    }
}




