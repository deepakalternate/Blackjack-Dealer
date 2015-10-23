package in.bits.blackjackdealer.bean;

import java.util.Random;

public class Card {
    
    Random randomizer;
    private int minSuit = 1;
    private int maxSuit = 4;
    private int minValue = 1;
    private int maxValue = 13;
    private int suit;
    private int value;
    
    public Card() {
        suit = randomizer.nextInt((maxSuit - minSuit) + 1) + minSuit;
        value = randomizer.nextInt((maxValue - minValue) + 1) + minValue;
    }

    /**
     * @return the suit
     */
    public int getSuit() {
        return suit;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
    
}
