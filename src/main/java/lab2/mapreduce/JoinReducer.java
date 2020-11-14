package lab2.mapreduce;

import lab2.dataformats.AirportIDPair;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class JoinReducer extends Reducer<AirportIDPair, Text, Text, Text> {
    protected void reduce(AirportIDPair key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iter = values.iterator();
        Text airportName = new Text(iter.next());

        float maxDelay = 0;
        float minDelay = Float.MAX_VALUE;
        float delaySum = 0;
        int c = 0;
        if (iter.hasNext()) {
            while (iter.hasNext()) {
                float curDelay = Float.parseFloat(iter.next().toString());
                if (maxDelay < curDelay) maxDelay = curDelay;
                if (minDelay > curDelay) minDelay = curDelay;
                c++;
                delaySum += curDelay;
            }
            context.write(airportName,
                    new Text("\n    min: " + minDelay + "   max: " + maxDelay + "   mean: " + delaySum/c));
        }
        /*String data = "";
        Text name = new Text(iter.next());
        while (iter.hasNext()) {
            Text d = iter.next();
            data = data + d.toString() + " ";
        }
        context.write(new Text("id: " + key.getAirportID()), new Text(name.toString() + " " + data));*/
    }
}
