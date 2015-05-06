package hmg;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;

import com.datasynapse.gridserver.GridServerException;
import com.datasynapse.gridserver.admin.AdminManager;
import com.datasynapse.gridserver.admin.BrokerAdmin;
import com.datasynapse.gridserver.admin.BrokerInfo;
import com.datasynapse.gridserver.admin.EngineAdmin;
import com.datasynapse.gridserver.admin.EngineInfo;
import com.datasynapse.gridserver.admin.ServiceAdmin;
import com.datasynapse.gridserver.admin.ServiceInfo;
import com.datasynapse.gridserver.driver.DriverManager;

public class HPCMetric implements Runnable {

	private Thread t;
	protected Vector<Metric> vector;
	private Calendar currenttime;
	protected Metric m;
	protected DriverGegevens d;
	private static DriverManager dm;

	public HPCMetric() {

	}

	public void start(Metric m, DriverGegevens d,  Vector<Metric> vector) throws IOException, InterruptedException {

		this.m = m;		
		this.d = d;
		this.vector = vector;

		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	protected String getEpochTimeStamp () {
		currenttime = Calendar.getInstance();		
		long time = currenttime.getTime().getTime() / 1000;

		return String.valueOf(time);
	}

	@Override
	public void run() {
		try {
			getHPCMetric();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void getHPCMetric() throws Exception {
		
		Properties p = new Properties();
		dm = new DriverManager();

		long serviceID = 0;
		String serviceStatus = "";
		String serviceName = "" ;

		p.put("DSUsername","admin");
		p.put("DSPassword","admin");
		p.put("DSPrimaryDirector", "http://192.168.178.104:8000");
		p.put("DSSecondaryDirector", "http://192.168.178.110:8000");

		//dm.fillDefaults();
		dm.addProperties(p);			

		while (true) {

			try {
				dm.connect();
			} catch (GridServerException g) {
				// TODO Auto-generated catch block
				g.printStackTrace();
				dm.disconnect();
			}

			if (dm.isConnected()) {

				BrokerAdmin ba = AdminManager.getBrokerAdmin();

				BrokerInfo[] bi = ba.getAllBrokerInfo();
				int brokerNumber = bi.length;

				for (int bn = 0; bn < brokerNumber; bn++) {

					if (bi[bn].isFailover()) {
						continue;
					}

					String[] bh = bi[bn].getBaseUrl().split("//");
					String[] bh2 = bh[1].split(":");
					String bhfinal = bh2[0];



					dm.connect(bi[bn].getName());

					if (dm.isConnected()) {

						try {
							ServiceAdmin sa = AdminManager.getServiceAdmin();
							ServiceInfo[] si = sa.getAllServiceInfo();	
							EngineAdmin ea = AdminManager.getEngineAdmin();
							EngineInfo[] ei = ea.getAllEngineInfo();

							System.out.println("I am "  + bhfinal );
							System.out.println("I have "  + bi[bn].getEngineCount() + " total Engines" );
							System.out.println("I have "  + bi[bn].getBusyEngineCount() + " busy Engines" );

							for (ServiceInfo s : si) {

								int pendingTasks =0;
								int runningTasks =0;
								int completedTasks =0;
								int serviceError =0;



								if (! s.getFinished() &&  ! s.getCompleted()) {

									serviceName = s.getServiceName();
									serviceID = s.getServiceId();
									serviceStatus = s.getStatus();

									if (serviceStatus.equalsIgnoreCase("Running, Task Errors")) {
										serviceError = 1;
									}

									pendingTasks += s.getPendingCount();
									runningTasks += s.getRunningCount();
									completedTasks += s.getCompletedCount();

									System.out.println("Service " + serviceName + " (" + serviceID + ")" + " has U: " + runningTasks);
									System.out.println("Service " + serviceName + " (" + serviceID + ")" + " has S: " + pendingTasks);
									System.out.println("Service " + serviceName + " (" + serviceID + ")" + " has E: " + serviceError);
									System.out.println("Service " + serviceName + " (" + serviceID + ")" + " has C: " + completedTasks);
								}
							}


							for (EngineInfo i : ei) {
								if (i.isBusy()) {

									System.out.println("Engine " + i.getUsername() + "-" + i.getInstance() + " spent " + i.getElapsedTime() + " millis running Service " + i.getServiceId());
								}
							}


						} catch ( NullPointerException e) {
							e.getStackTrace();
						}
					}
				}
			}
			dm.disconnect();
		}
	}
}
