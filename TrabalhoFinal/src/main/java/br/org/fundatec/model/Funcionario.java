package br.org.fundatec.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Classe Funcionario criada pelo.
 * @author Guilherme.
 * A classe é utilizada para armazenar dados do funcionario como o id e o nome.
 */

@Entity
@Table(name = "Funcionario")
public class Funcionario {

    /**
     * É o indentificador único para cada registro de funcionário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "funcionario_generator")
    @TableGenerator(name = "funcionario_generator",
            table = "chave",
            pkColumnName = "id",
            valueColumnName = "valor",
            allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    /**
     * É o nome do funcionário.
     */
    @Column(name = "nome")
    private String nome;

    public Funcionario(){
    }

    public Funcionario(String nome) {
        this.nome = nome;
    }

    public Funcionario(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * É usado para determinar se dois objetos são iguais com base nos atributos id e nome do funcionário.
     * @param o objeto a ser comparado
     * @return Retorna {@code true} se os objetos forem iguais, {@code false} se os objetos forem diferentes.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome);
    }

    /**
     * Baseado no id e no nome, é gerado o código hash.
     * @return Retorna o valor do código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    /**
     * É uma representação textual do objeto {@code Funcionario}.
     * @return Retorna uma String contendo os valores do ID, nome.
     */
    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
