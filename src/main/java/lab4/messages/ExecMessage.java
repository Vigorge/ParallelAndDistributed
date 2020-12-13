package lab4.messages;

import lab4.assists.TestData;

import java.util.ArrayList;

public class ExecMessage {
    private final String packID, funcName, jsScript, testName, expRes;
    private final ArrayList<Object> params;

    public ExecMessage(String packID, String funcName, String jsScript, String testName, String expRes, ArrayList<Object> params) {
        this.packID = packID;
        this.funcName = funcName;
        this.jsScript = jsScript;
        this.testName = testName;
        this.expRes = expRes;
        this.params = params;
    }

    public String getPackID() {
        return packID;
    }

    public String getFuncName() {
        return funcName;
    }

    public String getJsScript() {
        return jsScript;
    }

    public ArrayList<Object> getParams() {
        return params;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpRes() {
        return expRes;
    }
}
