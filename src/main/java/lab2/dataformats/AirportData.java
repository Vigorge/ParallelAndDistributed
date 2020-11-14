package lab2.dataformats;

public class AirportData {
    private String airportID;
    private String airportName;
    private static final String DOC_ID = "0";

    private static final String SEPARATOR = "\"";
    private static final int ID_COLUMN = 1;
    private static final int NAME_COLUMN = 3;

    public AirportData(String fileString) {
        String[] splits = fileString.split(SEPARATOR);
        airportID = splits[ID_COLUMN];
        airportName = splits[NAME_COLUMN];
    }

    public String getDocId() {
        return DOC_ID;
    }

    public String getAirportID() {
        return airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public String toString() {
        return "[" + airportID + ": " + airportName + "]";
    }
}
    