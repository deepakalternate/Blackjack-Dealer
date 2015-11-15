/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.bits.blackjackdealer.main;

import in.bits.blackjackdealer.net.DealerController;

/**
 *
 * @author tarun
 */
public class Main {
    public static void main(String args[]){
        DealerController dc = new DealerController();
        dc.start();
    }
}
