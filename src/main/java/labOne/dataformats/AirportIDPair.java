package labOne.dataformats;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportIDPair implements WritableComparable<AirportIDPair> {
    private String airportID;
    private String dataSetID;

    public AirportIDPair(String airportID, String dataSetID) {
        super();
        this.airportID = airportID;
        this.dataSetID = dataSetID;
    }

    public AirportIDPair() {
    }

    public String getAirportID() {
        return airportID;
    }

    public String getDataSetID() {
        return dataSetID;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(airportID);
        out.writeUTF(dataSetID);
    }

    public void readFields(DataInput in) throws IOException {
        airportID = in.readUTF();
        dataSetID = in.readUTF();
    }

    public int compareTo(AirportIDPair o) {
        return (airportID + dataSetID).compareTo(o.getAirportID() + o.getDataSetID());
    }

    public String toString() {
        return "<" + airportID + ", " + dataSetID + ">";
    }
}
