package br.org.fundatec.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

/**
 * Classe voto criada pelo.
 * @author Guilherme.
 * A classe é utilizada para armazenar dados do voto como o id, a data, o id do funcionario e o id do restaurante.
 */
@Entity
@Table(name = "Voto")
public class Voto {

    /**
     * É o indentificador único para cada registro de voto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "voto_generator")
    @TableGenerator(name = "voto_generator",
            table = "chave",
            pkColumnName = "id",
            valueColumnName = "valor",
            allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    /**
     * É a data que foi realizado o voto.
     * À data é armazenada como {@link TemporalType#DATE}
     */
    @Column
    @Temporal(TemporalType.DATE)
    private Calendar data;

    /**
     * Se referência com o funcionário que realizou o voto através do id.
     * Tem relacionamento ManyToOne com a entidade {@link Funcionario}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    private Funcionario funcionario;

    /**
     * Se referência ao restaurante votado através do id.
     * Relacionamento ManyToOne com a entidade {@link Restaurante}.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }


    public Voto() {
    }

    public Voto(Calendar data, Funcionario funcionario, Restaurante restaurante) {
        this.data = data;
        this.funcionario = funcionario;
        this.restaurante = restaurante;
    }

    /**
     * É usado para determinar se dois objetos são iguais com base nos atributos id e a data.
     * @param o objeto a ser comparado
     * @return Retorna {@code true} se os objetos forem iguais, {@code false} se os objetos forem diferentes.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(id, voto.id) && Objects.equals(data, voto.data);
    }

    /**
     * Baseado no id e na data, é gerado o código hash.
     * @return Retorna o valor do código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    /**
     * É uma representação textual do objeto {@code Voto}.
     * @return Retorna uma String contendo os valores do ID, data, funcionário e restaurante.
     */
    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", data=" + data +
                ", funcionario=" + funcionario +
                ", restaurante=" + restaurante +
                '}';
    }
}


