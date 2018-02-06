package realtime;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TransInfoReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{

	private DoubleWritable result = new DoubleWritable();
	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values,
			Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		double sum = 0.0;
		int cnt = 0;
		for (DoubleWritable value : values) {
			
			sum += value.get();
			cnt++;
			if (cnt % 5 == 0) {
				
				result.set(sum/5);
				context.write(key, result);
				
			}
			
		}
	}
}
