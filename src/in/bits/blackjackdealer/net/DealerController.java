/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.bits.blackjackdealer.net;

import in.bits.blackjackdealer.bean.Message;
import in.bits.blackjackdealer.bean.Type;
import in.bits.blackjackdealer.ui.DealerStats;
import in.bits.blackjackdealer.ui.DealerUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarun
 */
public class DealerController {

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
    }

    public void start() {
        while (true) {
            try {
                if (dealer.getIn().readObject() != null) {
                    Message message = (Message) dealer.getIn().readObject();

                    if (message.getType().getTypeOfMessage().equalsIgnoreCase("HIT")) {
                        dealer.sendMessage(dealer.getOut(), new Message(dealer.getDeck().getACard(), null, Type.CARD, message.getSender(), 0, null));
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
