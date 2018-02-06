package realtime;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TransInfoMapper extends Mapper<LongWritable, Text, Text, DoubleWritable>{
	
	private DoubleWritable outputValue = new DoubleWritable();
	private Text outputKey = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String cols[] = value.toString().split(",");
		outputKey.set(cols[1]);
		outputValue.set(Double.parseDouble(cols[1]));
		context.write(outputKey, outputValue);
		
	}

}
