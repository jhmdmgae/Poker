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

//    private ArrayList<Usuario> mesa;
    private Player[] mesa2 = {null, null, null, null, null, null, null, null};
    private Player[] mesaAtiva;
//    private boolean[] mesaAtiva = {false, false, false, false, false, false, false, false};
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
    private double pote;

    private List<Card> communityCards;
    Deck d;

    public Mesa() {

        dealer = 0;
        posicao = 0;
        quantPosicaoValida = -3;
        fase = 0;
        mudancaFase = true;
        primeiraRodada = true;
        apostaMinima = 20;
        apostaCorrente = apostaMinima;
        pote = 0;
    }

    public void jogada() {

        mesaAtiva = mesa2;
        alterarFase();
        d = new Deck();
        communityCards = new ArrayList<>();

        while (true) {

            //Entrega as cartas dos players
            if (posicao == 0) {
                for (int i = 0; i < 8; i++) {
                    if (mesaAtiva[i] != null) {
                        mesaAtiva[i].iniciarJogadaMesa(d, communityCards);
                    }
                }
            }

            verificarFimPartida();

            if (validarPosicao(posicao)) {

                System.out.println("Pote " + pote);
                System.out.println("quantPosicaoValida " + quantPosicaoValida);
                System.out.println("quant Jogadores " + calcularQuantJogadores());
                System.out.println("money " + mesaAtiva[posicao % 8].getMoney());
                System.out.println("Betting " + mesaAtiva[posicao % 8].getBetting());
                System.out.println("Aposta Corrent " + apostaCorrente);

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
                    colocarPote(apostaMinima / 2);
                } else if (primeiraRodada && quantPosicaoValida == -1) {
                    System.out.println(" --- Pagar Big Blind --- ");
                    retirarSaldo(posicao, apostaMinima);
                    colocarPote(apostaMinima);
                } else {
                    System.out.print("Vez: " + mesaAtiva[posicao % 8].getJogador().getNome());
                    if (fase == 2) {
                        mesaAtiva[posicao % 8].getHand();
                        System.out.println(mesaAtiva[posicao % 8]);
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
        for (int i = 0; i < mesaAtiva.length; i++) {
            if (mesaAtiva[i] != null) {
                System.out.println("getJogador " + mesaAtiva[i].getJogador().getNome());
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
//                case 1:
//                    posicaoSmallBlind(posicao);
//                    break;
//                case 2:
//                    posicaoBigBlind(posicao);
//                    primeiraRodada = false;
//                    break;
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
                        if (mesaAtiva[i] != null) {
                            mesaAtiva[i].setCommunityCards(communityCards);
                        }
                    }
//                    zerarBetting();
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
        if (mesaAtiva[posicao % 8] != null) {
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

    public void posicaoDealer(int i) {
        dealer = posicao;
    }

    private int acaoJogador(int posicao) {

        boolean pode = false;

        if (apostaCorrente < mesaAtiva[posicao % 8].getMoney()) {
            pode = true;
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println(" Digite 0 para Fold");
        System.out.println(" Digite 1 para Call");
        if (pode) {
            System.out.println(" Digite 2 para Raise");
            System.out.println(" Digite 3 para Apostar Tudo");
        }
        System.out.println("----------------------");

        acao = scan.nextInt();

        switch (acao) {
            case 0:
                inativarJogador(posicao);
                break;
            case 1:
                if (mesaAtiva[posicao % 8].getMoney() >= apostaCorrente) {
                    pagar(posicao, apostaCorrente);
                } else {
                    pagar(posicao, mesaAtiva[posicao % 8].getMoney());
                }
                break;
            case 2:
                if (pode) {
                    System.out.println(" Digite o quanto quer aumentar a aposta");
                    acao = scan.nextInt();
                    aumentar(posicao, acao);
                    quantPosicaoValida = 0;
                } else {
                    System.out.println("Valor inválido, por favor informa novamente!");
                    acaoJogador(posicao);
                }
                break;
            case 3:
                if (pode) {
                    aumentar(posicao, mesaAtiva[posicao % 8].getMoney() - apostaCorrente);
                    quantPosicaoValida = 0;
                } else {
                    System.out.println("Valor inválido, por favor informa novamente!");
                    acaoJogador(posicao);
                }
                break;
            default:
                System.out.println("Valor inválido, por favor informa novamente!");
                acaoJogador(posicao);
        }
        return acao;
    }

    public void listarJogadaresMesa() {
        System.out.print("[");
        if (mesaAtiva[0] != null) {
            System.out.print(mesaAtiva[0].getJogador().getNome() + " (" + mesaAtiva[0].getMoney() + ")" + " (" + mesaAtiva[0].getBetting() + ")");
        } else {
            System.out.print(" ");
        }
        for (int i = 1; i < mesaAtiva.length; i++) {
            if (mesaAtiva[i] != null) {
                System.out.print("," + mesaAtiva[i].getJogador().getNome() + " (" + mesaAtiva[i].getMoney() + ")" + " (" + mesaAtiva[i].getBetting() + ")");
            } else {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        if (this.fase == 2 || this.fase == 3) {
            for (int i = 0; i < mesaAtiva.length; i++) {
                if (mesaAtiva[i] != null) {
                    mesaAtiva[i].toString();
                }
            }
        }
    }

    private void inativarJogador(int posicao) {
        mesaAtiva[posicao % 8] = null;
        quantPosicaoValida--;
    }

    private void pagar(int posicao, double valor) {
        colocarPote(valor);
        retirarSaldo(posicao, valor);
    }

    private void aumentar(int posicao, double valor) {
        pagar(posicao, apostaCorrente);
        System.out.println("Paguei apostaCorrente " + apostaCorrente);
        apostaCorrente = apostaCorrente + valor;
        System.out.println("nova apostaCorrente " + apostaCorrente);
        System.out.println("pote " + pote);
        colocarPote(valor);
        System.out.println("novo pote " + pote);
        System.out.println("meu saldo " + mesaAtiva[posicao % 8].getMoney());
        retirarSaldo(posicao, valor);
        System.out.println("novo saldo " + mesaAtiva[posicao % 8].getMoney());
    }

    private void retirarSaldo(int posicao, double valor) {
        mesaAtiva[posicao % 8].setMoney(mesaAtiva[posicao % 8].getMoney() - valor);
        setarBetting(valor);
    }

    private void setarBetting(double valor) {
        mesaAtiva[posicao % 8].setBetting(valor + mesaAtiva[posicao % 8].getBetting());
    }

    private void colocarPote(double valor) {
        pote = pote + valor;
    }

    private void zerarBetting() {
        for (int i = 0; i < 8; i++) {
            if (mesaAtiva[i] != null) {
                mesaAtiva[i].setBetting(0.0);
            }
        }
        apostaCorrente = 0;
    }

    private void verificarFimPartida() {

        int quant = calcularQuantJogadores();

        System.out.println("calcularQuantJogadores " + calcularQuantJogadores());
        System.out.println("fase " + fase);
        System.out.println("quantPosicaoValida " + quantPosicaoValida);

        if (quant == 1 || (fase == 3 && quantPosicaoValida == 4)) {//calccular a quant de jogadores
            System.out.println("Fim da partida");

//            distribuir Pagamento
            d = new Deck();
            communityCards = new ArrayList<>();

            for (int i = 0; i < 8; i++) {
                if (mesaAtiva[i] != null) {
                    mesaAtiva[i].setBetting(0);
                }
            }

            mesaAtiva = mesa2;
            
            for (int i = 0; i < mesa2.length; i++) {
                if (mesa2[i] != null) {
                    System.out.println("getJogador " + mesa2[i].getJogador().getNome());
                    
                }
            }
            for (int i = 0; i < mesaAtiva.length; i++) {
                if (mesaAtiva[i] != null) {
                    System.out.println("getJogador " + mesaAtiva[i].getJogador().getNome());
                    
                }
            }

            
            posicao = 0;
            quantPosicaoValida = -3;
            fase = 0;
            mudancaFase = true;
            primeiraRodada = true;
            apostaMinima = 20;
            apostaCorrente = apostaMinima;
            pote = 0;
        }
    }
}

// começar n o 4 ok
// retirar as apostas de cada jogador ok
// apostar tudo ok
// modificar o dealer por partida
//validar scan ok
//verificr fim da partida  todos desistirem ou fim da partida
//cada turno mudar para começar no small blind
//criar pote na mesa ok


//desabilitar apostas quando jogador não tiver mais dinheiro
