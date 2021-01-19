package Pages;

public class TransferPage
{
    String SenderID;
    String ReceiverID;
    String Token;

    public TransferPage(String senderID, String receiverID, String token) {
        SenderID = senderID;
        ReceiverID = receiverID;
        Token = token;
        System.out.println("Hello @ Transfer Money Page");
    }

    public String getSenderID() {
        return SenderID;
    }

    public void setSenderID(String senderID) {
        SenderID = senderID;
    }

    public String getReceiverID() {
        return ReceiverID;
    }

    public void setReceiverID(String receiverID) {
        ReceiverID = receiverID;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
