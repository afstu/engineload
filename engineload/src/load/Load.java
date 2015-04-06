package load;

import logger.LogToGraphite;
import utils.Utils;

public abstract class Load implements Runnable {
	
	Utils u;
	Thread t;
	String threadName;
	LogToGraphite lg;

	public Load(String name) {
		this.threadName = name;
	}

	public void start(LogToGraphite lg, Utils u) {
		
		this.u = u;
		this.lg = lg;
		
		if (t == null) {
			t = new Thread(this, threadName);
			u.log("Starting load monitor thread..." + t.getName());
			t.start();
		}
	}
	
	@Override
	public void run() {
		getLoad();
	}
	
	public abstract void getLoad();

}
