package labZero;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static final String REGEX = "[_\\W&&[^а-яА-Я]&&[^-']]";
    private static final String MANY_SPACES = " +";

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString()
                .replaceAll(REGEX, " ")
                .replaceAll(MANY_SPACES, " ")
                .toLowerCase()
                .split(" ");
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }
}