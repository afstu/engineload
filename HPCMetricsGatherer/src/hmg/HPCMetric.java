package hmg;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.datasynapse.gridserver.GridServerException;
import com.datasynapse.gridserver.admin.AdminManager;
import com.datasynapse.gridserver.admin.BrokerAdmin;
import com.datasynapse.gridserver.admin.BrokerInfo;
import com.datasynapse.gridserver.admin.EngineInfo;
import com.datasynapse.gridserver.admin.ServiceInfo;
import com.datasynapse.gridserver.admin.ServiceInvocationInfo;
import com.datasynapse.gridserver.driver.DriverManager;

public class HPCMetric implements Runnable {

	private Thread t;
	private ConcurrentLinkedQueue<Metric> queue;
	private Calendar currenttime;
	protected Metric m;
	protected DriverGegevens d;
	protected List<Metric> finalMetricList;

	List<List<List<Metric>>> arrayListOflistsOfMetricsLists; 

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

		int counter = 0;
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

		try {
			DriverManager.connect();
		} catch (GridServerException g) {
			// TODO Auto-generated catch block
			g.printStackTrace();
			DriverManager.disconnect();
		}

		while (true) {

			if (DriverManager.isConnected()) {

				arrayListOflistsOfMetricsLists = new ArrayList<List<List<Metric>>>();
				//
				//  Metrics
				//

				// Datasynapse Classes
				BrokerAdmin ba = AdminManager.getBrokerAdmin();
				BrokerInfo[] bi = ba.getAllBrokerInfo();
				int brokerNumber = ba.getBrokerCount();

				for (int bn = 0; bn < brokerNumber; bn++) {

					if (bi[bn].isFailover()) {
						continue;
					}

					// fill the list of lists
					if (arrayListOflistsOfMetricsLists.size() != brokerNumber) {

						for (int lol = 0; lol < brokerNumber; lol++) {

							arrayListOflistsOfMetricsLists.add(new ArrayList<List<Metric>>());

							arrayListOflistsOfMetricsLists.get(lol).add(new ArrayList<Metric>());
							arrayListOflistsOfMetricsLists.get(lol).add(new ArrayList<Metric>());
							arrayListOflistsOfMetricsLists.get(lol).add(new ArrayList<Metric>());
							arrayListOflistsOfMetricsLists.get(lol).add(new ArrayList<Metric>());
							arrayListOflistsOfMetricsLists.get(lol).add(new ArrayList<Metric>());
							arrayListOflistsOfMetricsLists.get(lol).add(new ArrayList<Metric>());
							arrayListOflistsOfMetricsLists.get(lol).add(new ArrayList<Metric>());
						}
					}

					String[] bh = bi[bn].getBaseUrl().split("//");
					String[] bh2 = bh[1].split(":");
					String[] bh3 = bh2[0].split("\\.");
					String bhfinal = bh3[0];

					DriverManager.connect(bi[bn].getName());

					try {						
						// Housekeeping lists Engine and Session data
						List<ServiceData> serviceDataList = new ArrayList<ServiceData>();
						List<EngineData> engineDataList = new ArrayList<EngineData>();
						int pendingTasks =0;
						int runningTasks =0;
						int completedTasks =0;
						int serviceError =0;

						//
						// Broker Metrics
						//
						arrayListOflistsOfMetricsLists.get(bn).get(0).add(fillMetric(bhfinal, "TotalEngines", bi[bn].getEngineCount()));
						arrayListOflistsOfMetricsLists.get(bn).get(1).add(fillMetric(bhfinal, "BusyEngines", bi[bn].getBusyEngineCount()));

						//
						// Service Metrics
						//
						ServiceInfo[] si = AdminManager.getServiceAdmin().getAllServiceInfo();	

						if (si != null) {

							for (ServiceInfo s : si) {

								if (! s.getFinished() &&  ! s.getCompleted()) {

									serviceName = s.getServiceName();
									serviceID = s.getServiceId();
									serviceStatus = s.getStatus();

									ServiceData sd = new ServiceData();

									sd.setSessionID(serviceID);
									sd.setSessionName(serviceName);

									serviceDataList.add(sd);

									if (serviceStatus.equalsIgnoreCase("Running, Task Errors")) {
										serviceError = 1;
									}

									pendingTasks += s.getPendingCount();
									runningTasks += s.getRunningCount();
									completedTasks += s.getCompletedCount();
								}
							}
						}

						arrayListOflistsOfMetricsLists.get(bn).get(2).add(fillMetric(bhfinal, "U", runningTasks));
						arrayListOflistsOfMetricsLists.get(bn).get(3).add(fillMetric(bhfinal, "S", pendingTasks));
						arrayListOflistsOfMetricsLists.get(bn).get(4).add(fillMetric(bhfinal, "E", serviceError));
						arrayListOflistsOfMetricsLists.get(bn).get(5).add(fillMetric(bhfinal, "C", completedTasks));

						//reset counters
						pendingTasks = 0;
						runningTasks = 0;
						completedTasks = 0;
						serviceError = 0;

						//
						// Engine Metrics
						//						
						EngineInfo[] ei = AdminManager.getEngineAdmin().getAllEngineInfo();

						if (ei != null) {
							for (EngineInfo i : ei) {
								if (i.isBusy()) {

									EngineData e = new EngineData();

									e.setEngineName(i.getUsername() + "-" + i.getInstance());
									e.setComputeTime(i.getElapsedTime() / 1000);
									e.setServiceID(i.getServiceId());

									for (ServiceData s : serviceDataList) {
										if (s.getSessionID() == e.getServiceID()) {
											e.setSessionName(s.getSessionName().replaceAll("\\s+", ""));

										}
										engineDataList.add(e);
									}
								}
							}

							// get total compute time per session
							if (serviceDataList.size() != 0) {
								for (ServiceData s : serviceDataList) {

									ServiceInvocationInfo[] sii = AdminManager.getServiceAdmin().getSelectedInvocationInfo(s.getSessionID(), null);
									int totalComputeTime = 0;								
									String summedSessionName = s.getSessionName().replaceAll("\\s+", "");	

									for (int i = 0; i < sii.length; i++) {
										totalComputeTime += sii[i].getElapsedTime();
									}

									arrayListOflistsOfMetricsLists.get(bn).get(6).add(fillMetric(bhfinal + ".sessions." + summedSessionName, "ComputeTime" , (totalComputeTime / 1000)));
								}
							}
						}
					} catch ( NullPointerException e) {
						e.getStackTrace();
					}
				}
			}

			if (counter == 29) {

				finalMetricList = new ArrayList<Metric>();

				// for every broker
				for (int i = 0; i < arrayListOflistsOfMetricsLists.size(); i++) {

					int finalBrokerMaxTotal = 0;
					int finalBrokerMaxBusy = 0;

					String sessionName = "NotAName!";

					//
					// MAX Broker Metrics
					//

					finalBrokerMaxTotal = getMaxWaarde(arrayListOflistsOfMetricsLists.get(i).get(0));
					finalBrokerMaxBusy = getMaxWaarde(arrayListOflistsOfMetricsLists.get(i).get(1));

					finalMetricList.add(fillMetric(arrayListOflistsOfMetricsLists.get(i).get(0).get(0).getBron(), arrayListOflistsOfMetricsLists.get(i).get(0).get(0).getType(), finalBrokerMaxTotal));
					finalMetricList.add(fillMetric(arrayListOflistsOfMetricsLists.get(i).get(1).get(0).getBron(), arrayListOflistsOfMetricsLists.get(i).get(1).get(0).getType(), finalBrokerMaxBusy));

					List<List<Metric>> tempList = null;

					int finalSessionMaxU = 0;
					int finalSessionMaxS = 0;
					int finalSessionMaxE = 0;
					int finalSessionMaxC = 0;

					if (arrayListOflistsOfMetricsLists.get(i).get(2).size() != 0 ) {

						finalSessionMaxU = getMaxWaarde(arrayListOflistsOfMetricsLists.get(i).get(2));
						finalSessionMaxS = getMaxWaarde(arrayListOflistsOfMetricsLists.get(i).get(3));
						finalSessionMaxE = getMaxWaarde(arrayListOflistsOfMetricsLists.get(i).get(4));
						finalSessionMaxC = getMaxWaarde(arrayListOflistsOfMetricsLists.get(i).get(5));
					}

					if (arrayListOflistsOfMetricsLists.get(i).get(6).size() != 0 ) {
						// magic stuff :-)
						Collections.sort(arrayListOflistsOfMetricsLists.get(i).get(6));

						tempList = new ArrayList<List<Metric>>(); 

						int listCounter = -1;

						for (Metric m : arrayListOflistsOfMetricsLists.get(i).get(6)) {

							if (!sessionName.equalsIgnoreCase(m.getType())) {

								tempList.add( new ArrayList<Metric>());

								listCounter++;

								tempList.get(listCounter).add(m);

								sessionName = m.getType();
							} else {
								tempList.get(listCounter).add(m);
							}
						}
					}

					finalMetricList.add(fillMetric(arrayListOflistsOfMetricsLists.get(i).get(2).get(0).getBron(), arrayListOflistsOfMetricsLists.get(i).get(2).get(0).getType(), finalSessionMaxU));
					finalMetricList.add(fillMetric(arrayListOflistsOfMetricsLists.get(i).get(3).get(0).getBron(), arrayListOflistsOfMetricsLists.get(i).get(3).get(0).getType(), finalSessionMaxS));
					finalMetricList.add(fillMetric(arrayListOflistsOfMetricsLists.get(i).get(4).get(0).getBron(), arrayListOflistsOfMetricsLists.get(i).get(4).get(0).getType(), finalSessionMaxE));
					finalMetricList.add(fillMetric(arrayListOflistsOfMetricsLists.get(i).get(5).get(0).getBron(), arrayListOflistsOfMetricsLists.get(i).get(5).get(0).getType(), finalSessionMaxC));

					if (tempList != null) {
						int finalEngineComputeTime = 0;
						for (int tls = 0; tls < tempList.size(); tls++) {
							finalEngineComputeTime = getMaxWaarde(tempList.get(tls)); 
							finalMetricList.add(fillMetric(arrayListOflistsOfMetricsLists.get(i).get(6).get(0).getBron(), arrayListOflistsOfMetricsLists.get(i).get(6).get(0).getType(), finalEngineComputeTime));
						}
					}
				}	

				// all final metrics go in to the queue
				for (Metric m : finalMetricList) {

					queue.add(m);
				}

				// reset the counter and list of lists
				counter = 0;
				arrayListOflistsOfMetricsLists = new ArrayList<List<List<Metric>>>();
	
			} else {
				counter++;
			}

			Thread.sleep(2000);
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

	private int getMaxWaarde(List<Metric> list) {

		int maxWaarde = 0;

		for (Metric m : list) {

			if (maxWaarde < m.getWaarde()) {
				maxWaarde = m.getWaarde();
			}
			return maxWaarde;
		}
		return 0;
	}

	private int getSumWaarde(List<Metric> list) {

		int sumWaarde = 0;

		for (Metric m : list) {

			sumWaarde += m.getWaarde();
		}

		return sumWaarde;
	}

}
