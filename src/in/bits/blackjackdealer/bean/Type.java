package in.bits.blackjackdealer.bean;

public enum Type {
    
    CARD("CARD"), //Unicast
    FOLD("FOLD"), //To Server
    BUST("BUST"), //To Server
    HIT("HIT"), //To Server
    RESULT("RESULT"), //Broadcast
    REPLAY("REPLAY"), //Broadcast
    ACCEPT("ACCEPT"), //To Server
    QUIT("QUIT"); //To Server
    
    Type(String typeOfMessage){
        this.typeOfMessage = typeOfMessage;
    }
    
    private String typeOfMessage;

    /**
     * 
     * @return the type of message
     */
    public String getTypeOfMessage(){
       return typeOfMessage;
    }
    
}