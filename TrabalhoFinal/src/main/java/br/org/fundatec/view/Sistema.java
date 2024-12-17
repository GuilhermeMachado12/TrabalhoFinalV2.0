package br.org.fundatec.view;

import br.org.fundatec.controller.VotacaoController;
import br.org.fundatec.exception.VotosException;
import br.org.fundatec.model.Voto;
import br.org.fundatec.util.TecladoUtil;

import java.util.Calendar;

public class Sistema {

    private static VotacaoController controller = new VotacaoController();
    private static boolean sair = false;

    public static void main(String[] args) {
        while (!sair) {
            menu();
            int opcao = TecladoUtil.lerInteiro("Informa uma Opcao:");
            executaAcao(opcao);
        }
    }
    private static void menu() {
        System.out.println("________________________");
        System.out.println("(1) Adicionar funcionario");
        System.out.println("(2) Adicionar restaurante");
        System.out.println("(3) Cadastrar voto");
        System.out.println("(4) Listar funcionários");
        System.out.println("(5) Listar restaurantes");
        System.out.println("(6) Apurar votação");
        System.out.println("(7) Sair");
        System.out.println("________________________");
    }
    private static void executaAcao(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    adicionarFuncionario();
                    break;
                case 2:
                    adicionarRestaurante();
                    break;
                case 3:
                    cadastrarVoto() ;
                    break;
                case 4:
                    listarFuncionarios();
                    break;
                case 5:
                    listarRestaurantes();
                    break;
                case 6:
                    apurarVotacao() ;
                    break;
                case 7:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opcao invalida!!");
                    break;
            }
        }catch (VotosException e){
            System.out.println(e.getMessage());
        }
    }


    private static void listarRestaurantes() throws VotosException{
        System.out.println(controller.listarRestaurante());
    }

    private static void listarFuncionarios() throws VotosException {
        System.out.println(controller.listarFuncionarios());
    }



    private static void adicionarRestaurante() throws VotosException {
        String nome = TecladoUtil.lerString("Informe o nome do restaurante que será inserido: ");
        controller.adicionarRestaurante(nome);
    }

    private static void adicionarFuncionario() throws VotosException {
        String nome = TecladoUtil.lerString("Informe o nome do  funcionário que será inserido: ");
        controller.adicionarFuncionario(nome);
    }

    private static void cadastrarVoto() throws VotosException {
        System.out.println("Lista de Funcionários:");
        System.out.println(controller.listarFuncionarios());
        String nomeFuncionario = TecladoUtil.lerString("Qual o nome do funcionário que realizará o voto?");
        System.out.println("Lista de Restaurantes:");
        System.out.println(controller.listarRestaurante());
        Integer id = TecladoUtil.lerInteiro("Informe o id do restaurante:");
        Calendar hj = Calendar.getInstance();

        controller.cadastrarVoto(nomeFuncionario,id,hj);
    }

    private static void apurarVotacao() throws VotosException {
        System.out.println(controller.apurarVotacao());
    }
}
