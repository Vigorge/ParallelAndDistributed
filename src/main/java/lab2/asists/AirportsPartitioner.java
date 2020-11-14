package lab2.asists;

import lab2.dataformats.AirportIDPair;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportsPartitioner extends Partitioner<AirportIDPair, Text> {
    public int getPartition(AirportIDPair pair, Text text, int i) {
        return (pair.getAirportID().hashCode() & Integer.MAX_VALUE) % i;
    }
}
