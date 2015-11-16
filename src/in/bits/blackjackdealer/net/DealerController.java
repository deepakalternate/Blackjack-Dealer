/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.bits.blackjackdealer.net;

import in.bits.blackjack.bean.Message;
import in.bits.blackjack.bean.Type;
import in.bits.blackjackdealer.ui.DealerStats;
import in.bits.blackjackdealer.ui.DealerUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author tarun
 */
public class DealerController extends Thread {

    private Dealer dealer;
    private DealerUI ui;
    private DealerStats stats;

    public DealerController() {
        ui = new DealerUI();
        ui.setVisible(true);
        ui.addConnectActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dealer = new Dealer(ui.getServerIP(), ui.getServerPort());
                    ui.setVisible(false);
                    stats = ui.getDealerStats();
                    stats.setVisible(true);
                    updateStats();
                } catch (IOException ex) {
                    ui.setErrorMessage("Error connecting to Server!!!");
                }

            }
        });

        ui.getDealerStats().addQuitActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int in = JOptionPane.showConfirmDialog(ui.getDealerStats(), "Are You Sure?", "Are You Sure?", JOptionPane.YES_NO_OPTION);

                if (in == JOptionPane.YES_OPTION) {
                    try {
                        dealer.sendMessage(dealer.getOut(), new Message(null, "DEALER", Type.EXIT, null, 0, null));
                    } catch (IOException ex) {
                        Logger.getLogger(DealerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ui.getDealerStats().dispose();
                    System.exit(0);
                }
            }
        });

    }

    public void run() {
        while (true) {
            try {
                Message message = (Message) dealer.getIn().readObject();
                if (message != null) {
                    System.out.println("Message received: " + message);
                    if (message.getType().getTypeOfMessage().equalsIgnoreCase("HIT")) {
                        dealer.sendMessage(dealer.getOut(), new Message(dealer.getDeck().getACard(), "DEALER", Type.CARD, message.getSender(), 0, null));
                        updateStats();
                    }
                }

            } catch (IOException | NullPointerException | ClassNotFoundException ex) {
                //Logger.getLogger(DealerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateStats() {
        stats.setTotalCards(52);
        stats.setDealtCards(52 - dealer.getDeckSize());
        stats.setRemainingCards(dealer.getDeckSize());
        stats.setStats(dealer.getDeck().getDeckOfCards());
    }
}
