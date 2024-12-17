package br.org.fundatec.util;

import java.util.Scanner;

/**
 *
 */
public class TecladoUtil {
    /**
     *
     * @param mensagem
     * @return
     */
    public static String lerString(String mensagem) {
        return  inicializaTeclado(mensagem).next();
    }

    /**
     *
     * @param mensagem
     * @return
     */

    public static  Integer lerInteiro(String mensagem) {
        return  inicializaTeclado(mensagem).nextInt();
    }

    /**
     *
     * @param mensagem
     * @return
     */

    private static Scanner inicializaTeclado(String mensagem) {
        System.out.println(mensagem);
        return new Scanner(System.in);
    }
}
