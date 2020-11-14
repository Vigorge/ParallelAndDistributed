package labOne.asists;

import labOne.dataformats.AirportIDPair;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {
    protected GroupComparator() {
        super(AirportIDPair.class, true);
    }

    public int compare(WritableComparable o1, WritableComparable o2) {
        AirportIDPair key1 = (AirportIDPair) o1;
        AirportIDPair key2 = (AirportIDPair) o2;
        return key1.getAirportID().compareTo(key2.getAirportID());
    }
}
