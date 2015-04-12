package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand implements Comparable<Hand> {

    private final List<Card> cards;
    private final int[] value = new int[6];

    public int[] getValue() {
        return value;
    }

    private void initialize() {
        int[] ranks = new int[13];
        //miscellaneous cards that are not otherwise significant
        int[] orderedRanks = new int[5];
        boolean flush = true,
                straight = false;
        int sameCards = 1,
                sameCards2 = 1;
        int largeGroupRank = 0,
                smallGroupRank = 0;
        int index = 0;

        /* Clean the ranks. ranks[0] is never used */
        for (int x = 0; x <= 12; x++) {
            ranks[x] = 0;
        }
        /* Verify which ranks are present in a hand */
        for (int x = 0, size = cards.size(); x < size; x++) {
            ranks[cards.get(x).getRank()]++;
        }
//        System.out.println("ranks: " + Arrays.toString(ranks));
        /* Verify if it is a flush */
        for (int x = 0, size = cards.size(); x < size - 1; x++) {
            if (cards.get(x).getSuit() != cards.get(x + 1).getSuit()) {
                flush = false;
            }
        }

        /* Verify for a high card, pair, two pair, three and four of a kind, full house */
        for (int x = 12; x >= 0; x--) {
            if (ranks[x] > sameCards) {
                if (sameCards != 1) //if sameCards was not the default value
                {
                    sameCards2 = sameCards;
                    smallGroupRank = largeGroupRank;
                }

                sameCards = ranks[x];
                largeGroupRank = x;

            } else if (ranks[x] > sameCards2) {
                sameCards2 = ranks[x];
                smallGroupRank = x;
            }
        }

        smallGroupRank = (smallGroupRank == 0) ? 13 : smallGroupRank;
        largeGroupRank = (largeGroupRank == 0) ? 13 : largeGroupRank;
        
        /* Verify if it is a straight */
//        int topStraightValue = 0;
        for (int x = 0; x <= 8; x++) //can't have straight with lowest value of more than 10
        {
            if (ranks[x] == 1
                    && ranks[x + 1] == 1
                    && ranks[x + 2] == 1
                    && ranks[x + 3] == 1
                    && ranks[x + 4] == 1) {
                straight = true;
//                topStraightValue = x + 4; //4 above bottom value
                break;
            }
        }

        if (ranks[0] == 1 && !straight) //if ace, run this before because ace is highest card
        {
            orderedRanks[index] = 13;
            index++;
        }

        for (int x = 12; x >= 1; x--) {
            if (ranks[x] == 1) {
                orderedRanks[index] = x; //if ace
                index++;
            }
        }

        if (ranks[9] == 1
                && ranks[10] == 1
                && ranks[11] == 1
                && ranks[12] == 1
                && ranks[0] == 1) //ace high
        {
            straight = true;
//            topStraightValue = 14; //higher than king
        }

//        System.out.println("orderedRanks: " + Arrays.toString(orderedRanks));
        /* Clean value array */
        for (int x = 0; x <= 5; x++) {
            value[x] = 0;
        }

        //start hand evaluation
        if (sameCards == 1 && !flush && !straight) {
            value[0] = 1; // high card
            value[1] = orderedRanks[0];
            value[2] = orderedRanks[1];
            value[3] = orderedRanks[2];
            value[4] = orderedRanks[3];
            value[5] = orderedRanks[4];
        }

        if (sameCards == 2 && sameCards2 == 1) {
            value[0] = 2;
            value[1] = largeGroupRank;
            value[2] = orderedRanks[0];
            value[3] = orderedRanks[1];
            value[4] = orderedRanks[2];
        }

        if (sameCards == 2 && sameCards2 == 2) //two pair
        {
            value[0] = 3;
            //rank of greater pair
            value[1] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank;
            value[2] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank;
            value[3] = orderedRanks[0];  //extra card
        }

        if (sameCards == 3 && sameCards2 != 2) {
            value[0] = 4; // three of a kind
            value[1] = largeGroupRank;
            value[2] = orderedRanks[0];
            value[3] = orderedRanks[1];
        }

        if (straight && !flush) {
            value[0] = 5; // straight
            value[1] = orderedRanks[0];
            value[2] = orderedRanks[1];
            value[3] = orderedRanks[2];
            value[4] = orderedRanks[3];
            value[5] = orderedRanks[4];
        }

        if (flush && !straight) {
            value[0] = 6; // flush
            value[1] = orderedRanks[0]; //tie determined by ranks of cards
            value[2] = orderedRanks[1];
            value[3] = orderedRanks[2];
            value[4] = orderedRanks[3];
            value[5] = orderedRanks[4];
        }

        if (sameCards == 3 && sameCards2 == 2) {
            value[0] = 7; // full house
            value[1] = largeGroupRank;
            value[2] = smallGroupRank;
        }

        if (sameCards == 4) {
            value[0] = 8; // four of a kind
            value[1] = largeGroupRank;
            value[2] = orderedRanks[0];
        }

        if (straight && flush) {
            value[0] = 9; // straight flush
            value[1] = orderedRanks[0];
            value[2] = orderedRanks[1];
            value[3] = orderedRanks[2];
            value[4] = orderedRanks[3];
            value[5] = orderedRanks[4];
        }
//        System.out.println("value: " + Arrays.toString(value));
    }

    public Hand(List<Card> c) {
        cards = c;
        initialize();
    }

    public Hand(Deck d) {
        cards = new ArrayList();
        for (int x = 0; x < 5; x++) {
            cards.add(d.drawFromDeck());
        }
        initialize();
    }

    public void display() {
        System.out.println(getHandName());
    }

    public String getHandName() {
        String s;
        switch (value[0]) {
            case 1:
                s = Card.rankAsString(value[1]) + " high card";
                break;
            case 2:
                s = "pair of " + Card.rankAsString(value[1]) + "\'s";
                break;
            case 3:
                s = "two pair " + Card.rankAsString(value[1]) + " "
                        + Card.rankAsString(value[2]);
                break;
            case 4:
                s = "three of a kind " + Card.rankAsString(value[1]) + "\'s";
                break;
            case 5:
                s = Card.rankAsString(value[1]) + " high straight";
                break;
            case 6:
                s = "flush";
                break;
            case 7:
                s = "full house " + Card.rankAsString(value[1]) + " over "
                        + Card.rankAsString(value[2]);
                break;
            case 8:
                s = "four of a kind " + Card.rankAsString(value[1]);
                break;
            case 9:
                s = "straight flush " + Card.rankAsString(value[1]) + " high";
                break;
            default:
                s = "error in Hand.display: value[0] contains invalid value";
        }
        s = "        " + s;

        return s;
    }

    public void displayAll() {
        System.out.println(getCardsName());
    }

    public String getCardsName() {
        StringBuilder sb = new StringBuilder();

//        for (int x = 0, size = cards.size(); x < size; x++) {
//            sb.append(cards.get(x)).append(" | " );
//        }
//        
        for (Card card : cards) {
            sb.append(card).append(" | ");
        }
        return sb.toString();
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('\n').append("Nome da Mão: ").append(getHandName());
        sb.append('\n').append("Mão: ").append(getCardsName());

        return sb.toString();
    }

    @Override
    public int compareTo(Hand that) {
        for (int x = 0; x < 6; x++) {
            if (this.value[x] > that.value[x]) {
                return 1;
            } else if (this.value[x] < that.value[x]) {
                return -1;
            }
        }
        return 0; //if hands are equal
    }

    public static void main(String[] args) {
        Hand h = new Hand(new ArrayList<Card>() {
            {
                add(new Card(0, 0));
                add(new Card(0, 12));
                add(new Card(0, 11));
                add(new Card(0, 10));
                add(new Card(0, 9));
            }
        });
        System.out.println(h);
    }
}
