package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import servlets.entidade.Usuario;

/**
 *
 * @author João Henrique 2
 */
public class Mesa {

    private ArrayList<Usuario> mesa;
    private Player[] mesa2 = {null, null, null, null, null, null, null, null};
    private Usuario jogador;
    private int dealer;
    private int posicao;
    private boolean primeiraRodada;
    private int quantPosicaoValida;
    private int fase;
    private int acao;
    private boolean mudancaFase;
    private int apostaMinima;
    private double apostaCorrente;

    private List<Card> communityCards;
    Deck d;

    public Mesa() {

        mesa = new ArrayList();
        dealer = 0;
        posicao = 0;
        quantPosicaoValida = -3;
        fase = 0;
        mudancaFase = true;
        primeiraRodada = true;
        apostaMinima = 20;
        apostaCorrente = apostaMinima;
    }

    public void jogada() {

        alterarFase();
        d = new Deck();
        communityCards = new ArrayList<>();

        while (true) {

            //Entrega as cartas dos players
            if (posicao == 0) {
                for (int i = 0; i < 8; i++) {
                    if (mesa2[i] != null) {
                        mesa2[i].iniciarJogadaMesa(d, communityCards);
                    }
                }
            }

            if (validarPosicao(posicao)) {

//                System.out.println(" fase = " + fase);
//                System.out.println(" posicao = " + posicao);
//                System.out.println(" quantPosicaoValida = " + quantPosicaoValida);
//                System.out.println("Betting " + mesa2[posicao % 8].getBetting());
//                System.out.println("Aposta Corrent " + apostaCorrente);

                if (quantPosicaoValida == calcularQuantJogadores()) {
                    mudancaFase = true;
                    alterarFase();
                    quantPosicaoValida = 0;
                }

                if (primeiraRodada && quantPosicaoValida == -3) {
                    System.out.println(" --- Distribuir Cartas --- ");
                } else if (primeiraRodada && quantPosicaoValida == -2) {
                    System.out.println(" --- Pagar Small Blind  --- ");
                    
                    retirarSaldo(posicao, apostaMinima / 2);
                } else if (primeiraRodada && quantPosicaoValida == -1) {
                    System.out.println(" --- Pagar Big Blind --- ");
                    retirarSaldo(posicao, apostaMinima);
                } else {
                    System.out.print("Vez: " + mesa2[posicao % 8].getJogador().getNome());
                    if (fase == 2) {
                        mesa2[posicao % 8].getHand();
                        System.out.println(mesa2[posicao % 8]);
                    }
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
                    //adicionar cartas na mesa
                    communityCards.add(d.drawFromDeck());
                    communityCards.add(d.drawFromDeck());
                    communityCards.add(d.drawFromDeck());
                    System.out.println("Cartas na mesa");
                    for (int i = 0; i < communityCards.size(); i++) {
                        System.out.println(communityCards.get(i));
                    }
                    //adicionar cartas da mesa no player
                    for (int i = 0; i < 8; i++) {
                        if (mesa2[i] != null) {
                            mesa2[i].setCommunityCards(communityCards);
                        }
                    }
                    zerarBetting();
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

    public void addJogador(Player usuario, int posicao) {

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
        System.out.println(" Digite 3 para Apostar Tudo");
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
            case 4:
                aumentar(posicao, mesa2[posicao % 8].getMoney()-apostaCorrente);
                
                break;
        }

        return acao;
    }

    public void listarJogadaresMesa() {
        System.out.print("[");
        if (mesa2[0] != null) {
            System.out.print(mesa2[0].getJogador().getNome() + " (" + mesa2[0].getMoney() + ")");
        } else {
            System.out.print(" ");
        }
        for (int i = 1; i < mesa2.length; i++) {
            if (mesa2[i] != null) {
                System.out.print("," + mesa2[i].getJogador().getNome() + " (" + mesa2[i].getMoney() + ")");
            } else {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        if (this.fase == 2) {
            mesa2[0].toString();
            mesa2[2].toString();
            mesa2[3].toString();
            mesa2[6].toString();
        }
    }

    private void inativarJogador(int posicao) {
        listarJogadaresMesa();
        mesa2[posicao % 8] = null;
        listarJogadaresMesa();
    }

    private void pagar(int posicao, double valor) {
        retirarSaldo(posicao, valor);
    }

    private void aumentar(int posicao, double valor) {

//        System.out.println("valor " + valor);
//        System.out.println("Betting " + mesa2[posicao % 8].getBetting());
//        System.out.println("Aposta Corrent " + apostaCorrente);

        apostaCorrente = apostaCorrente + valor;
        quantPosicaoValida = 0;
//        mesa2[posicao % 8].setBetting(apostaCorrente);
        pagar(posicao, apostaCorrente);

//        System.out.println("valor " + valor);
//        System.out.println("Betting " + mesa2[posicao % 8].getBetting());
//        System.out.println("Aposta Corrent " + apostaCorrente);
    }

    private void retirarSaldo(int posicao, double valor) {

        System.out.println("money " + mesa2[posicao % 8].getMoney());
        
        System.out.println("valor " + valor);
        System.out.println("Betting " + mesa2[posicao % 8].getBetting());
        System.out.println("Aposta Corrent " + apostaCorrente);

        mesa2[posicao % 8].setMoney(mesa2[posicao % 8].getMoney() - (valor-mesa2[posicao % 8].getBetting()));
        mesa2[posicao % 8].setBetting(valor);
//        mesa2[posicao % 8].setMoney(mesa2[posicao % 8].getMoney() - mesa2[posicao % 8].getBetting());

        System.out.println("valor " + valor);
        System.out.println("Betting " + mesa2[posicao % 8].getBetting());
        System.out.println("Aposta Corrent " + apostaCorrente);

        System.out.println("money " + mesa2[posicao % 8].getMoney());
    }

    private void zerarBetting() {
        for (int i = 0; i < 8; i++) {
            if (mesa2[i] != null) {
                mesa2[i].setBetting(0.0);
            }
        }
        apostaCorrente = 0;
    }
}

// começar n o 4
// retirar as apostas de cada jogador
// apostar tudo
// modificar o dealer por partida
