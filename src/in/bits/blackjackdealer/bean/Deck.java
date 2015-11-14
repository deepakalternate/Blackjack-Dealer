package in.bits.blackjackdealer.bean;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private LinkedList<Card> deckOfCards;
    private Random randomizer;

    public Deck() {
        deckOfCards = new LinkedList<>();
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
        int minSuit = 1;
        int maxSuit = 4;
        int minValue = 1;
        int maxValue = 13;
        int suitSelected;
        int cardNumberSelected;

        while (deckOfCards.size() < 52) {
            suitSelected = randomizer.nextInt((maxSuit - minSuit) + 1) + minSuit;
            cardNumberSelected = randomizer.nextInt((maxValue - minValue) + 1) + minValue;

            if (cardNumberSelected >= 10) {
                deckOfCards.add(new Card(Suit.valueOf(getSuitName(suitSelected)), cardNumberSelected, 10));
            } else {
                deckOfCards.add(new Card(Suit.valueOf(getSuitName(suitSelected)), cardNumberSelected, cardNumberSelected));
            }
        }

    }

    public LinkedList<Card> getDeckOfCards() {
        return deckOfCards;
    }
    
    public Card getACard(){
        return deckOfCards.remove();
    }
    
    public int sizeOfDeck(){
        return deckOfCards.size();
    }

}
