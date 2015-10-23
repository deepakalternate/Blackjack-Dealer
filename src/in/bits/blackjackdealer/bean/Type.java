package in.bits.blackjackdealer.bean;

public enum Type {
    
    CHAT("CHAT"),
    UNICAST("UNICAST"),
    REQUEST("REQUEST"),
    ACCEPT("ACCEPT"),
    REJECT("REJECT"),
    DISCONNECT("DISCONNECT"),
    LOGOUT("LOGOUT"),
    HELLO("HELLO"),
    LIST("LIST"),
    CONFLICT("CONFLICT");
    
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