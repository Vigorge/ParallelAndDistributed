package lab4.assists;

import org.apache.htrace.shaded.fasterxml.jackson.annotation.JsonCreator;
import org.apache.htrace.shaded.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PackageData {
    @JsonProperty("packageID")
    private final String packageID;
    @JsonProperty("jsScript")
    private final String jsScript;
    @JsonProperty("functionName")
    private final String functionName;
    @JsonProperty("tests")
    private final ArrayList<TestData> tests;

    @JsonCreator
    public PackageData(@JsonProperty("packageID") String packageID,
                       @JsonProperty("jsScript") String jsScript,
                       @JsonProperty("functionName") String functionName,
                       @JsonProperty("tests") ArrayList<TestData> tests)
    {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public ArrayList<TestData> getTests() {
        return tests;
    }
}
