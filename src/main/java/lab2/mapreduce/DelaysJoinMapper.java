package lab2.mapreduce;

import lab2.dataformats.AirportIDPair;
import lab2.dataformats.DelayData;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DelaysJoinMapper extends Mapper<LongWritable, Text, AirportIDPair, Text> {
    private static final String ZERO_DELAY = "0.00";

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() != 0) {
            DelayData data = new DelayData(value.toString());
            if (!data.getDelay().equals(ZERO_DELAY) && !data.getDelay().isEmpty()) {
                context.write(new AirportIDPair(data.getAirportID(), data.getDocId()), new Text(data.getDelay()));
            }
        }
    }
}
