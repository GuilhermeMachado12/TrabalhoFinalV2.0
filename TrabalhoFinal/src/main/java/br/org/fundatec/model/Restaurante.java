package br.org.fundatec.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Classe Restaurante criada pelo.
 * @author Guilherme.
 * A classe é utilizada para armazenar dados do restaurante como o id e o nome.
 */
@Entity
@Table(name = "Restaurante")
public class Restaurante {

    /**
     * É o indentificador único para cada registro de restaurante.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "restaurante_generator")
    @TableGenerator(name = "restaurante_generator",
            table = "chave",
            pkColumnName = "id",
            valueColumnName = "valor",
            allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    /**
     *  É o nome do restaurante.
     */
    @Column(name = "nome")
    private String nome;

    public Restaurante(){
    }

    public Restaurante(String nome) {
        this.nome = nome;
    }

    public Restaurante(Integer id, String nome) {
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
     * É usado para determinar se dois objetos são iguais com base nos atributos id e nome do restaurante.
     * @param o objeto a ser comparado
     * @return Retorna {@code true} se os objetos forem iguais, {@code false} se os objetos forem diferentes.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
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
     * É uma representação textual do objeto {@code Restaurante}.
     * @return Retorna uma String contendo os valores do ID, nome.
     */
    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
