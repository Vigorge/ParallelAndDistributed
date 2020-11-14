package lab3.functions;

import lab3.serializable.FlightStats;
import org.apache.spark.api.java.function.Function2;

public class MergeDelInfo implements Function2<FlightStats, FlightStats, FlightStats> {
    @Override
    public FlightStats call(FlightStats delInfo1, FlightStats delInfo2) throws Exception {
        return FlightStats.mergeDels(delInfo1, delInfo2);
    }
}
