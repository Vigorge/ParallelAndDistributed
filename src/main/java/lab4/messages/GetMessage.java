package lab4.asists;

public class GetMessage {
    private final String packID;

    public GetMessage(String packID, String result) {
        this.packID = packID;
    }

    public String getPackID() {
        return packID;
    }
}
