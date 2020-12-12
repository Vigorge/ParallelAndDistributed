package lab4.asists;

import java.io.Serializable;

public class TestResults implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String packID, result;

    public TestResults(String packID, String result) {
        this.packID = packID;
        this.result = result;
    }

    public String getPackID() {
        return packID;
    }

    public String getResult() {
        return result;
    }
}