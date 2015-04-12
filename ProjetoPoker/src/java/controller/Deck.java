package controller;


import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList();

        for (short a = 0; a <= 3; a++) {
            for (short b = 0; b <= 12; b++) {
                cards.add(new Card(a, b));
            }
        }                

        Collections.shuffle(cards);        
    }

    public Card drawFromDeck() {
        return cards.remove(cards.size() - 1);
    }

    public int getTotalCards() {
        return cards.size();
        //we could use this method when making 
        //a complete poker game to see if we needed a new deck
    }
}
