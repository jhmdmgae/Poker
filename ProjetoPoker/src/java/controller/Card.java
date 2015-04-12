package controller;

public class Card {

    private final int rank, suit;

    private static final String[] suits = {"hearts", "spades", "diamonds", "clubs"};
    private static final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "Jack", "Queen", "King"};

    public static String rankAsString(int __rank) {
        if (__rank == 13) {
            __rank = 0;
        }
        return ranks[__rank];
    }

    public Card(int suit, int rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public @Override
    String toString() {
        return ranks[rank] + " of " + suits[suit];
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }
}
