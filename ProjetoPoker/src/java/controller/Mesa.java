package controller;

import java.util.ArrayList;
import java.util.Scanner;
import servlets.entidade.Usuario;

/**
 *
 * @author João Henrique 2
 */
public class Mesa {

    private ArrayList<Usuario> mesa;
    private Usuario[] mesa2 = {null, null, null, null, null, null, null, null};
    private Usuario jogador;
    private int dealer;
    private int posicao;
    private boolean primeiraRodada;
    private int quantPosicaoValida;
    private int fase;
    private int acao;
    private boolean mudancaFase;
    private int apostaMinima;
    private int apostaCorrente;

    public Mesa() {

        mesa = new ArrayList();
        dealer = 0;
        posicao = 0;
        quantPosicaoValida = 0;
        fase = 0;
        mudancaFase = true;
        primeiraRodada = true;
        apostaMinima = 20;
        apostaCorrente = apostaMinima;
    }

    public void jogada() {

        alterarFase();

        while (true) {

            if (validarPosicao(posicao)) {

                if (quantPosicaoValida == calcularQuantJogadores()) {
                    mudancaFase = true;
                    alterarFase();
                    quantPosicaoValida = 0;
                }                
                
                if (primeiraRodada && quantPosicaoValida == 0) {
                    System.out.println(" --- Distribuir Cartas --- ");
                } else if (primeiraRodada && quantPosicaoValida == 1) {
                    System.out.println(" --- Pagar Small Blind  --- ");
                    retirarSaldo(posicao, apostaMinima/2);
                } else if (primeiraRodada && quantPosicaoValida == 2) {
                    System.out.println(" --- Pagar Big Blind --- ");
                    retirarSaldo(posicao, apostaMinima);
                } else {
                    System.out.print("Vez: " + mesa2[posicao % 8].getNome());
                    acaoJogador(posicao);
                }

                testarPrimeiraRodada();

                quantPosicaoValida++;
                System.out.println("");
                
                listarJogadaresMesa();

            }

            proximaPosicao(posicao);

        }
    }

    public int calcularQuantJogadores() {

        int quant = 0;

        for (int i = 0; i < mesa2.length; i++) {
            if (mesa2[i] != null) {
                quant++;
            }
        }
        return quant;
    }

    public void testarPrimeiraRodada() {

        if (primeiraRodada) {
            switch (quantPosicaoValida) {
                case 0:
                    posicaoDealer(posicao);
                    break;
                case 1:
                    posicaoSmallBlind(posicao);
                    break;
                case 2:
                    posicaoBigBlind(posicao);
                    primeiraRodada = false;
                    break;
            }
        }
    }

    public void alterarFase() {

        if (mudancaFase == true) {
            switch (fase) {
                case 0:
                    System.out.println("------------------------------");
                    System.out.println("          Fase: Pré-Flop");
                    System.out.println("------------------------------");
                    fase++;
                    break;
                case 1:
                    System.out.println("------------------------------");
                    System.out.println("          Fase: Flop");
                    System.out.println("------------------------------");
                    fase++;
                    break;
                case 2:
                    System.out.println("------------------------------");
                    System.out.println("          Fase: Turn");
                    System.out.println("------------------------------");
                    fase++;
                    break;
                case 3:
                    System.out.println("------------------------------");
                    System.out.println("          Fase: River");
                    System.out.println("------------------------------");
                    fase++;
                    break;
            }
            mudancaFase = false;
        }

    }

    public boolean validarPosicao(int posicao) {

        if (mesa2[posicao % 8] != null) {
            return true;
        } else {
            return false;
        }

    }

    public int proximaPosicao(int i) {
        return posicao++;
    }

    public void addJogador(Usuario usuario, int posicao) {

        mesa2[posicao] = usuario;

    }

    public void removerJogador(int posicao) {

        mesa.remove(posicao);

    }

    public void posicaoDealer(int i) {

//        System.out.print(" - Dealer: " + (i % 8));
        dealer = posicao;

    }

    public void posicaoSmallBlind(int i) {

//        System.out.print(" - Small Blind: " + i % 8);

    }

    public void posicaoBigBlind(int i) {

//        System.out.print(" - Big Blind: " + i % 8);

    }

    private int acaoJogador(int posicao) {

        Scanner scan = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println(" Digite 0 para Fold");
        System.out.println(" Digite 1 para Call");
        System.out.println(" Digite 2 para Raise");
        System.out.println("----------------------");

        acao = scan.nextInt();

        switch (acao) {
            case 0:
                inativarJogador(posicao);
                break;
            case 1:
                pagar(posicao, apostaCorrente);
                break;
            case 2:
                System.out.println(" Digite o quanto quer aumentar a aposta");
                acao = scan.nextInt();
                aumentar(posicao, acao);
                break;
        }

        return acao;

    }

    public void listarJogadaresMesa() {
        System.out.print("[");
        if (mesa2[0] != null) {
            System.out.print(mesa2[0].getNome()+" ("+mesa2[0].getDinheiro()+")");
        } else {
            System.out.print("-");
        }
        for (int i = 1; i < mesa2.length; i++) {
            if (mesa2[i] != null) {
                System.out.print(","+mesa2[i].getNome()+" ("+mesa2[i].getDinheiro()+")");
            } else {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private void inativarJogador(int posicao) {
        listarJogadaresMesa();
        mesa2[posicao % 8] = null;
        listarJogadaresMesa();
    }

    private void pagar(int posicao, int valor) {
        retirarSaldo(posicao, valor);
    }

    private void aumentar(int posicao, int valor) {
        apostaCorrente = valor;
//        System.out.print(" apostaCorrente " + apostaCorrente);
    }

    private void retirarSaldo(int posicao, int valor) {
        mesa2[posicao % 8].setDinheiro(mesa2[posicao % 8].getDinheiro() - valor);
//        System.out.print(" - Novo saldo de " + mesa2[posicao % 8].getNome());
//        System.out.print(" = " + mesa2[posicao % 8].getDinheiro());
    }
}
