package br.org.fundatec.model;

/**
 * Classe VotoPorRestaurante criada pelo.
 * @author Guilherme.
 * A classe é utilizada para armazenar dados da classe VotoPorRestaurante como o id e o voto.
 */
public class VotoPorRestaurante {

    private String restaurantes;
    private Long voto;

    public VotoPorRestaurante(String restaurantes, Long voto) {
        this.restaurantes = restaurantes;
        this.voto = voto;
    }

    public String getRestaurantes() {
        return restaurantes;
    }

    public Long getVoto() {
        return voto;
    }

    /**
     * É uma representação textual do objeto {@code Restaurante}.
     * @return Retorna uma String contendo os valores do ID, nome.
     */
    @Override
    public String toString() {
        return "VotoPorRestaurante{" +
                "restaurantes='" + restaurantes + '\'' +
                ", voto=" + voto +
                '}';
    }
}
