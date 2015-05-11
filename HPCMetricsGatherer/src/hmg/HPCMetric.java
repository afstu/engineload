package hmg;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
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
	private ConcurrentLinkedQueue<Metric> queue;
	private Calendar currenttime;
	protected Metric m;
	protected DriverGegevens d;
	protected List<Metric> metricList;

	public HPCMetric() {

	}

	public void start(DriverGegevens d,  ConcurrentLinkedQueue<Metric> queue) throws IOException, InterruptedException {

		this.d = d;
		this.queue = queue;

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

	public void getHPCMetric() throws Exception {

		Properties p = new Properties();

		long serviceID = 0;
		String serviceStatus = "";
		String serviceName = "" ;

		p.put("DSUsername",d.getDriverUser());
		p.put("DSPassword",d.getDriverPassword());
		p.put("DSPrimaryDirector", "http://" + d.getHPCDirectorUrl() + ":" + d.getHPCDirectorPoort());
		p.put("DSLogUseJavaConfig", "true");
		p.put("DSSecondaryDirector", "http://" + d.getHPCDirectorUrl() + ":" + d.getHPCDirectorPoort());

		DriverManager.fillDefaults();
		DriverManager.addProperties(p);	



		while (true) {

			metricList = new ArrayList<Metric>();

			try {
				DriverManager.connect();
			} catch (GridServerException g) {
				// TODO Auto-generated catch block
				g.printStackTrace();
				DriverManager.disconnect();
			}

			if (DriverManager.isConnected()) {

				BrokerAdmin ba = AdminManager.getBrokerAdmin();

				BrokerInfo[] bi = ba.getAllBrokerInfo();
				int brokerNumber = bi.length;
				


				for (int bn = 0; bn < brokerNumber; bn++) {

					if (bi[bn].isFailover()) {
						continue;
					}
					
					int pendingTasks =0;
					int runningTasks =0;
					int completedTasks =0;
					int serviceError =0;

					String[] bh = bi[bn].getBaseUrl().split("//");
					String[] bh2 = bh[1].split(":");
					String[] bh3 = bh2[0].split("\\.");
					String bhfinal = bh3[0];

					DriverManager.connect(bi[bn].getName());

					if (DriverManager.isConnected()) {

						try {
							ServiceAdmin sa = AdminManager.getServiceAdmin();
							ServiceInfo[] si = sa.getAllServiceInfo();	
							EngineAdmin ea = AdminManager.getEngineAdmin();
							EngineInfo[] ei = ea.getAllEngineInfo();


							// Engine Metrics
							metricList.add(fillMetric(bhfinal, "TotalEngines", bi[bn].getEngineCount()));

							metricList.add(fillMetric(bhfinal, "BusyEngines", bi[bn].getBusyEngineCount()));

							for (ServiceInfo s : si) {



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

								}
							}
							
							// Director Metrics
							metricList.add(fillMetric(bhfinal, "U", runningTasks));
							metricList.add(fillMetric(bhfinal, "S", pendingTasks));
							metricList.add(fillMetric(bhfinal, "E", serviceError));
							metricList.add(fillMetric(bhfinal, "C", completedTasks));

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

			for (Metric m : metricList) {
				try {
					queue.add(m);	
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
			Thread.sleep(60000);
		}
	}

	/**
	 * @param MetricBron
	 * @param MetricName
	 * @param Waarde
	 */
	private Metric fillMetric(String MetricBron, String MetricName, int Waarde) {
		m = new Metric();
		m.setBron(MetricBron);
		m.setTijdStip(getEpochTimeStamp());
		m.setType(MetricName);
		m.setWaarde(Waarde);
		return m;
	}
}
