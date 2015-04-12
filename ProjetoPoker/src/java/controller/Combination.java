package controller;

import controller.Card;
import controller.Deck;
import controller.Hand;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Combination<G> {

    private int quantity;
    private List<G> list;
    private List<List<G>> resultList;
    private G[] resultArray;

    public Combination() {
        resultList = new ArrayList<>();
    }

    public List<List<G>> calc(List<G> n, int k) {
        if (k <= 0 || k > n.size()) {
            throw new IllegalArgumentException();
        }

        if (n.size() == k) {
            resultList.add(n);
        } else {
            this.list = n;
            this.quantity = k;
            int total = (int) combinacao(n.size(), k);
            this.resultArray = (G[]) new Object[total];
            combinar(0, n.size() - k, 0);
        }

        return resultList;
    }
int count = 0;
    private void combinar(int inicio, int fim, int profundidade) {

        if ((profundidade + 1) >= quantity) {
            for (int x = inicio; x <= fim; x++) {
                resultArray[profundidade] = list.get(x);
//                System.out.println(profundidade);
                resultList.add(new ArrayList<G>() {
                    {
                        for (int i = 0; i < quantity; i++) {
                            add(resultArray[i]);
                        }
                    }
                });
            }
        } else {
            for (int x = inicio; x <= fim; x++) {
                resultArray[profundidade]
                        = list.get(x);
                combinar(x + 1, fim + 1, profundidade + 1);
            }
        }
    }

    public BigDecimal fatorial(int n) {
        BigDecimal fat = new BigDecimal(1);

        for (int i = 2; i <= n; i++) {
            fat = fat.multiply(new BigDecimal(i));
        }

        return fat;
    }

    public BigDecimal multi(long number, long stop) {
        BigDecimal multi = new BigDecimal(1);

        while (number > stop) {
            multi = multi.multiply(new BigDecimal(number--));
        }

        return multi;
    }

    public long combinacao(int n, int k) {
        BigDecimal result = null;

        if (n == k) {
            result = new BigDecimal(1);
        } else if (k <= n / 2) {
            result = multi(n, n - k).divide(fatorial(k));
        } else {
            result = multi(n, k).divide(fatorial(n - k));
        }

        return result.longValue();
    }

    public static void main(String args[]) {
        Deck deck = new Deck();
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cards.add(deck.drawFromDeck());
        }

        Combination<Card> combCards = new Combination<>();
//        System.out.println(combCards.combinacao(0, 0));
        ArrayList<Hand> hands = new ArrayList<Hand>();

        for (List<Card> listCards : combCards.calc(cards, 5)) {
            hands.add(new Hand(listCards));
//            for (Card card : listCards) {
//                System.out.print(card + "   |    ");
//            }
//            System.out.println("");
        }
        System.out.println("QTD: " + hands.size());
//        Hand.displayHandsFrequency();

        System.out.println(Collections.max(hands));
    }
}
