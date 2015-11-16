package in.bits.blackjack.bean;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> initialDeck;
    private LinkedList<Card> deckOfCards;
    private Random randomizer;

    public Deck() {

        initialDeck = new ArrayList<>();
        randomizer = new Random();
        populateDeck();
    }

    private String getSuitName(int index) {
        switch (index) {
            case 1:
                return "HEARTS";
            case 2:
                return "SPADES";
            case 3:
                return "DIAMONDS";
            case 4:
                return "CLUBS";
            default:
                return "";
        }
    }

    private void populateDeck() {
        LinkedHashSet<Card> tempDeck = new LinkedHashSet<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                if (j >= 10) {
                    initialDeck.add(new Card(Suit.valueOf(getSuitName(i)), j, 10));
                } else {
                    initialDeck.add(new Card(Suit.valueOf(getSuitName(i)), j, j));
                }
            }
        }
        //System.out.println("Initial List------------>");
        //System.out.println(initialDeck);
        while (tempDeck.size() < 52) {
            tempDeck.add(initialDeck.get(randomizer.nextInt(52)));
        }
        deckOfCards = new LinkedList<Card>(tempDeck);

    }

    public LinkedList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public Card getACard() {
        return deckOfCards.remove();
    }

    public int sizeOfDeck() {
        return deckOfCards.size();
    }

}
