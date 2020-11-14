package labTwo.functions;

import labTwo.serializable.FlightStats;
import org.apache.spark.api.java.function.Function;

public class CreateDelInfo implements Function<String, FlightStats> {
    @Override
    public FlightStats call(String del) throws Exception {
        if (del.isEmpty()) {
            return new FlightStats(0F, 0, 1, 1);
        }
        if (Float.parseFloat(del) != 0F) {
            return new FlightStats(Float.parseFloat(del), 1, 0, 1);
        }
        return new FlightStats(0F, 0, 0, 1);
    }
}
