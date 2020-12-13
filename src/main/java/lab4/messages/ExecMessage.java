package lab4.messages;

import lab4.assists.TestData;

public class ExecMessage {
    private final String packID;
    private final TestData testData;

    public ExecMessage(String packID, TestData testData) {
        this.packID = packID;
        this.testData = testData;
    }

    public String getPackID() {
        return packID;
    }

    public TestData getTestData() {
        return testData;
    }
}
