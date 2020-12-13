package lab4.messages;

public class GetMessage {
    private final String packID;

    public GetMessage(String packID) {
        this.packID = packID;
    }

    public String getPackID() {
        return packID;
    }
}
