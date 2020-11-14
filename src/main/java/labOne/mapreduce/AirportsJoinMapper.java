package labOne.mapreduce;

import labOne.dataformats.AirportData;
import labOne.dataformats.AirportIDPair;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportsJoinMapper extends Mapper<LongWritable, Text, AirportIDPair, Text> {
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() != 0) {
            AirportData data = new AirportData(value.toString());
            context.write(new AirportIDPair(data.getAirportID(), data.getDocId()), new Text(data.getAirportName()));
        }
    }
}
