/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.bits.blackjackdealer.test;

import in.bits.blackjackdealer.bean.Deck;
import in.bits.blackjackdealer.net.DealerController;

/**
 *
 * @author tarun
 */
public class Test {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println("Size of Deck:" +deck.getDeckOfCards().size());
        System.out.println("--------------------------------------------------");
        System.out.println(deck.getDeckOfCards());
        System.out.println("--------------------------------------------------");
        System.out.println(deck.getACard());
        System.out.println("Size after removal:"+deck.getDeckOfCards().size());
        
        //DealerController dc = new DealerController();
    }
}
