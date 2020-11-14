package lab2.dataformats;

public class DelayData {
    private String airportID;
    private String delay;
    private static final String DOC_ID = "1";

    private static final String SEPARATOR = ",";
    private static final int ID_COLUMN = 14;
    private static final int DELAY_COLUMN = 18;

    public DelayData(String fileString) {
        String[] splits = fileString.split(SEPARATOR);
        airportID = splits[ID_COLUMN];
        delay = splits[DELAY_COLUMN];
    }

    public String getDocId() {
        return DOC_ID;
    }

    public String getAirportID() {
        return airportID;
    }

    public String getDelay() {
        return delay;
    }

    public String toString() {
        return "[" + airportID + ": " + delay + "]";
    }
}
