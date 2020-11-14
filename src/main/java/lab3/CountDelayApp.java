package lab3;

import lab3.functions.*;
import lab3.serializable.*;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;


public class CountDelayApp {
    private static final String DELIMETER = ",";
    //airports.csv
    private static final int PORT_ID_COL = 0;
    private static final int PORT_NAME_COL = 1;
    //flights.csv
    private static final int ORIGIN_PORT_ID_COL = 11;
    private static final int DEST_PORT_ID_COL = 14;
    private static final int DELAY_COL = 18;

    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("CountDelayApp");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //Collecting data from airports.csv
        Map<String, String> airportNameMap = sc
                .hadoopFile("airports.csv", TextInputFormat.class, LongWritable.class, Text.class)
                .filter(tblStr -> tblStr._1().get() != 0)
                .map(valStr -> TableRowData.parseRowQuoted(valStr._2().toString(), DELIMETER))
                .mapToPair(row -> new Tuple2<>(row.getValue(PORT_ID_COL), row.getValue(PORT_NAME_COL)))
                .collectAsMap();

        final Broadcast<Map<String, String>> airportsBroadcasted =
                sc.broadcast(airportNameMap);

        //Collecting data from flights.csv
        JavaPairRDD<Tuple2<String, String>, FlightStats> delayData = sc
                .hadoopFile("flights.csv", TextInputFormat.class, LongWritable.class, Text.class)
                .filter(tblStr -> tblStr._1.get() != 0)
                .map(valStr -> TableRowData.parseRow(valStr._2().toString(), DELIMETER))
                .mapToPair(row -> new Tuple2<>(new Tuple2<>(row.getValue(ORIGIN_PORT_ID_COL), row.getValue(DEST_PORT_ID_COL)), row.getValue(DELAY_COL)))
                .combineByKey(new CreateDelInfo(), new ModDelInfo(), new MergeDelInfo());

        //Preparing result
        JavaRDD<String> result = delayData.map(
                pair -> FlightStats.formResultString(pair, airportsBroadcasted.value())
        );

        result.saveAsTextFile("output");
    }
}
