package lab4.asists;

import java.util.ArrayList;

public class ResultMessage {
    private final String packID;
    private final ArrayList<String> results;

    public ResultMessage(String packID, ArrayList<String> result) {
        this.packID = packID;
        this.results = result;
    }

    public String getPackID() {
        return packID;
    }

    public ArrayList<String> getResults() {
        return results;
    }
}
