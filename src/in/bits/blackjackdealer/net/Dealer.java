/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.bits.blackjackdealer.net;

import in.bits.blackjack.bean.Card;
import in.bits.blackjack.bean.Deck;
import in.bits.blackjack.bean.Message;
import in.bits.blackjack.bean.Type;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author tarun
 */
public class Dealer implements DealerInterface {

    private Socket socket;
    private Deck deck;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public Dealer(String hostname, String port) throws IOException{
            deck = new Deck();
       
            socket = new Socket();
            socket.bind(new InetSocketAddress(4343));
            socket.connect(new InetSocketAddress(hostname, Integer.parseInt(port)));
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Connected to the Server:"+hostname+"/"+port+" using 4343");
            sendMessage(out, new Message(null, "DEALER", Type.ISDEALER, null, 0, null));
    }
    
    
    @Override
    public Card dealCard() {
        return deck.getACard();
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }
    
    public int getDeckSize(){
        return deck.sizeOfDeck();
    }

    public Deck getDeck() {
        return deck;
    }
    
    public void sendMessage(ObjectOutputStream out,Message message) throws IOException{
        System.out.println(message);
        out.writeObject(message);
    }
    
}
