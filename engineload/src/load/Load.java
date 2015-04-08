package load;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import utils.Utils;

public abstract class Load implements Runnable {

	Utils u;
	Thread t;
	String threadName;
		

	public Load(String name, Utils utils) {
		this.threadName = name;
		this.u = utils;
	}

	public void start() throws IOException, InterruptedException {
		
		if (t == null) {
			t = new Thread(this, threadName);
			
			t.start();
		}
	}

	public void buildMetricAndWrite(int metric, String source) {
		StringBuilder sb = new StringBuilder();

		sb.append(u.getHostPath().get(2)).append(".").append(u.getHostPath().get(3)).append(".").append(u.getHostPath().get(4))

		.append(".").append(u.getMyHostName()).append(".").append(source).append(" ").append(metric)

		.append(" ").append(u.getEpochTimeStamp()).append("\n");

		writeGraphite(sb.toString());
	}

	private void writeGraphite(String completeMetric) {
		try {
			int port = Integer.parseInt(u.getHostPath().get(1));
			Socket soc = new Socket(u.getHostPath().get(0), port);
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

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

	@Override
	public void run() {
		getLoad();
	}

	public abstract void getLoad();

}
