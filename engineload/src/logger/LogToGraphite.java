package logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import utils.Utils;

public class LogToGraphite {

	Utils u;
	List<String> hostPath;
	
	public LogToGraphite(Utils u) throws IOException, InterruptedException {
		
		this.u = u;
		
		hostPath = u.getNodePortPathEnvFromConf();
			
		u.pinger(hostPath.get(0));
		
	}


	
	public void buildMetricAndWrite(int metric, String source) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(hostPath.get(2)).append(".").append(hostPath.get(3)).append(".").append(hostPath.get(4))
		
		.append(".").append(u.getMyHostName()).append(".").append(source).append(" ").append(metric)
		
		.append(" ").append(u.getEpochTimeStamp()).append("\n");
		
		// u.log(sb.toString());
		
		writeGraphite(sb.toString());
	}
	
	private void writeGraphite(String completeMetric) {
		
		try {
			
			int port = Integer.parseInt(hostPath.get(1));
			Socket soc = new Socket(hostPath.get(0), port);
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			
			// u.log("Writing metric to Graphite..." + completeMetric);
			
			out.println(completeMetric);
			out.flush();
			out.close();
			soc.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
