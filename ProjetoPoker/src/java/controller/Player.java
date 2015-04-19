package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import servlets.entidade.Usuario;

/**
 *
 * @author Lourivaldo
 */
public class Player implements Comparable<Player> {

    private Card[] myCards;
    private List<Card> communityCards;
    private double money;
    private double betting;
    //regra das apostas

    private Usuario jogador;

    public Usuario getJogador() {
        return jogador;
    }

    public void setJogador(Usuario jogador) {
        this.jogador = jogador;
    }

    public Player() {
        this.myCards = new Card[2];
        this.money = 0;
        this.betting = 0;
    }

    public Player(Deck d, List<Card> community) {
        this();

        this.communityCards = community;
        this.myCards[0] = d.drawFromDeck();
        this.myCards[1] = d.drawFromDeck();
    }
    //add deck e cartas depois
    public void iniciarJogadaMesa(Deck d, List<Card> community) {

        this.communityCards = community;
        this.myCards[0] = d.drawFromDeck();
        this.myCards[1] = d.drawFromDeck();
    }

    public Card[] getMyCards() {
        return myCards;
    }

    public void setMyCards(Card[] myCards) {
        this.myCards = myCards;
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public void setCommunityCards(List<Card> communityCards) {
        this.communityCards = communityCards;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getBetting() {
        return betting;
    }

    public void setBetting(double betting) {
//        setMoney(getMoney() - (betting-this.betting));
        this.betting = betting;
    }

    public Hand getHand() {
        List<Hand> hands = new ArrayList<>();
//        Combinations<Card> combinations = new Combinations<Card>();
        Combinations combinations = new Combinations();
        ArrayList<Card> arrayList = new ArrayList<Card>() {
            {
                addAll(Arrays.asList(myCards));
                addAll(communityCards);
            }
        };
//        List<List<Card>> listHands = combination.calc(arrayList, 5);
        List<List<Card>> listHands = combinations.combine(arrayList, 5);

        for (List<Card> cards : listHands) {
            hands.add(new Hand(cards));
        }

        return Collections.max(hands);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("####Jogador ").append('\n');
        sb.append("Na Mão: ");
        for (Card myCard : myCards) {
            sb.append(myCard).append(" | ");
        }
        sb.append(getHand().toString());
        return sb.toString();
    }

    @Override
    public int compareTo(Player that) {
        return getHand().compareTo(that.getHand());
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        List<Card> c = new ArrayList<>();

        Player player = new Player(d, c);
        c.add(d.drawFromDeck());
        c.add(d.drawFromDeck());
        c.add(d.drawFromDeck());
        player.getHand();
        System.out.println(player);
        c.add(d.drawFromDeck());
        player.getHand();
        System.out.println(player);
        c.add(d.drawFromDeck());
        player.getHand();
        System.out.println(player);
    }

}
