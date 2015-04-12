package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lourivaldo
 */
public class Combinations {

    private int quantity = 0;
    private List<Card> list;
    private List<List<Card>> resultList;
    private Card[] tempList;

    public Combinations() {
    }

    public List<List<Card>> combine(List<Card> n, int k) {
        this.list = n;
        this.quantity = k;

        resultList = new ArrayList<>();
        tempList = new Card[5];

        combinar(0, n.size() - k, 0);
        return resultList;
    }

    private void combinar(int inicio, int fim, int profundidade) {

        if ((profundidade + 1) >= quantity) {
            for (int x = inicio; x <= fim; x++) {
//                System.out.println(inicio + " " + fim + " " + profundidade);
                tempList[profundidade] = list.get(x);

                resultList.add(new ArrayList<Card>() {
                    {
                        for (int i = 0; i < quantity; i++) {
                            add(tempList[i]);
                        }
                    }
                });
            }
        } else {
            for (int x = inicio; x <= fim; x++) {
                tempList[profundidade] = list.get(x);
                combinar(x + 1, fim + 1, profundidade + 1);
            }
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            cards.add(deck.drawFromDeck());
        }

        Combinations c = new Combinations();
//        List<Hand> hands = new ArrayList<>();
        int[] frequencyHand = new int[10];
        Hand hand;
        int[] value;
        for (List<Card> cardList : c.combine(cards, 5)) {
//            frequencyHand[0]++;
            hand = new Hand(cardList);
            value = hand.getValue();
            frequencyHand[value[0]]++;
//            hands.add(hand);
        }

        int totalFrequencyHand = 0;
        for (int i = 0; i < frequencyHand.length; i++) {
            totalFrequencyHand += frequencyHand[i];
        }
        for (int i = 0; i < frequencyHand.length; i++) {
            int g = frequencyHand[i];
            double p = ((double) g / (double) totalFrequencyHand) * 100;
            System.out.println(g + " - " + p + "%");
        }

//        System.out.println(Collections.max(hands));
    }
}
