package lab3.functions;

import lab3.serializable.FlightStats;
import org.apache.spark.api.java.function.Function2;

public class ModDelInfo implements Function2<FlightStats, String, FlightStats> {
    @Override
    public FlightStats call(FlightStats delInfo, String del) throws Exception {
        delInfo.addFlight();
        if (del.isEmpty()) {
            delInfo.addCancelled();
            return delInfo;
        }
        if (Float.parseFloat(del) != 0F) {
            delInfo.addDelayed();
            delInfo.checkMaxDelay(Float.parseFloat(del));
            return delInfo;
        }
        return delInfo;
    }
}
