package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lourivaldo
 */
public class Table {

    private Deck deck;
    private List<Player> players;
    private List<Card> communityCards;

    public Table() {
        this.deck = new Deck();
        this.players = new ArrayList<Player>();
        this.communityCards = new ArrayList<Card>();
    }

    public double getMoney() {
        double sum = 0;
        for (Player player : players) {
            sum += player.getBetting();
        }
        return sum;
    }

    public void addPlayer(Player p) {
        p.setCommunityCards(communityCards);
        players.add(p);
    }

    public void addCard() {
        communityCards.add(deck.drawFromDeck());
    }

    public void distributePlayerCards() {
        for (Player player : players) {
            Card[] c = {deck.drawFromDeck(), deck.drawFromDeck()};
            player.setMyCards(c);
        }
    }

    public Player winner() {
        return Collections.max(players);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n').append("Cartas Comunitarias: ").append('\n').append('\n');

        for (Card card : communityCards) {
            sb.append("->").append(card).append('\n');
        }

        sb.append('\n').append("Cartas por jogador: ").append('\n');
        for (Player player : players) {
            sb.append(player);
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        //pre-flop        
        Table table = new Table();
        //adicionando jogadores
        Player player = new Player();
        Player player1 = new Player();
        Player player2 = new Player();

        player.setMoney(1000);
        player1.setMoney(1000);
        player2.setMoney(1000);

        table.addPlayer(player);
        table.addPlayer(player1);
        table.addPlayer(player2);

        //
        table.distributePlayerCards();

        //flop
        table.addCard();
        table.addCard();
        table.addCard();
        player.setBetting(50);
        player1.setBetting(50);
        player2.setBetting(50);
        //turn
        table.addCard();
        player.setBetting(50);
        player1.setBetting(50);
        player2.setBetting(50);
        //river
        table.addCard();
        player.setBetting(50);
        player1.setBetting(50);
        player2.setBetting(50);

        System.out.println(table);

        System.out.println("Ganhador: ");
        System.out.println(table.winner());
    }

}
