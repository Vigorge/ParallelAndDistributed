package lab3.serializable;

import scala.Tuple2;

import java.io.Serializable;
import java.util.Map;

public class FlightStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private float maxDelay;
    private int delayedAmount;
    private int cancelledAmount;
    private int flightsAmount;

    public FlightStats(float maxDelay, int delayedAmount, int cancelledAmmount, int flightsAmmount) {
        this.maxDelay = maxDelay;
        this.delayedAmount = delayedAmount;
        this.cancelledAmount = cancelledAmmount;
        this.flightsAmount = flightsAmmount;
    }

    public float getMaxDelay() {
        return maxDelay;
    }

    public int getDelayedAmount() {
        return delayedAmount;
    }

    public int getCancelledAmount() {
        return cancelledAmount;
    }

    public int getFlightsAmount() {
        return flightsAmount;
    }

    public String toString() {
        return "FlightStats{" +
                "maxDelay=" + maxDelay +
                ", delayedAmount=" + delayedAmount +
                ", cancelledAmount=" + cancelledAmount +
                ", flightsAmount=" + flightsAmount +
                '}';
    }

    public void addDelayed() {
        delayedAmount++;
    }

    public void addCancelled() {
        cancelledAmount++;
    }

    public void addFlight() {
        flightsAmount++;
    }

    public void checkMaxDelay(float del) {
        maxDelay = maxDelay > del ? maxDelay : del;
    }

    public static FlightStats mergeDels(FlightStats di1, FlightStats di2) {
        return new FlightStats(Float.max(di1.getMaxDelay(), di2.getMaxDelay()),
                di1.getDelayedAmount() + di2.getDelayedAmount(),
                di1.getCancelledAmount() + di2.getCancelledAmount(),
                di1.getFlightsAmount() + di2.getFlightsAmount());
    }

    public static String formResultString(Tuple2<Tuple2<String, String>, FlightStats> dataPair, Map<String, String> dictionary) {
        String originName = dictionary.get(dataPair._1()._1());
        String destName = dictionary.get(dataPair._1()._2());
        FlightStats delInfo = dataPair._2();

        return originName + "-->" + destName + "\n" +
                "   MaxDelay = " + delInfo.getMaxDelay() +
                "   " + "% Delayed = " + ((float) delInfo.getDelayedAmount()/delInfo.getFlightsAmount())*100 + "%" +
                "   " + "% Cancelled = " + ((float) delInfo.getCancelledAmount()/delInfo.getFlightsAmount())*100 + "%" +
                "   " + "Total = " + delInfo.getFlightsAmount();
    }
}
