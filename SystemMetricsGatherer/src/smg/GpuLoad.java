package smg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class GpuLoad.
 */
public class GpuLoad extends Load {
	
	/** The p. */
	private Process p;
	
	/** The std out. */
	private BufferedReader stdOut;
	
	/** The metric. */
	private Metric metric;

	/**
	 * Instantiates a new gpu load.
	 */
	public GpuLoad() {
		super();
		// metric = new Metric();
	}

	/* (non-Javadoc)
	 * @see smg.Load#getLoad()
	 */
	@Override
	public synchronized void getLoad() {
		try {
			int numGpu = getGpuNum();
			int loadCache[] = new int[12];

			while (true) {

				for (int i = 0; i < 12; i++) {
					loadCache[i] = getGpuLoad(numGpu);
					Thread.sleep(5000);
				}

				int max = 0;

				for (int i = 0 ; i < loadCache.length ; i++) {
					if ( max < loadCache[i]) {
						max = loadCache[i];
					}
					metric.setWaarde(max);
					metric.setType("GPU");
					metric.setTijdStip(getEpochTimeStamp());
				}
				
				while ( ! vector.isEmpty() ) {
					try {
						wait();
					} catch (InterruptedException e) {
						Logger.getAnonymousLogger().severe("Something bad happened while waiting for the Vector.");
						
					} 
				}
				vector.add(0, metric);
				notifyAll();
			}
		} catch (InterruptedException e) {
			Logger.getAnonymousLogger().severe("Thread was interrupted while running " + GpuLoad.class);
		} catch (IOException e) {
			Logger.getAnonymousLogger().severe("Something bad happened while trying to count GPU's!!");
		}
	}

	/**
	 * Gets the gpu num.
	 *
	 * @return the gpu num
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private int getGpuNum() throws IOException {
		ArrayList<String> gpus = new ArrayList<String>(); 		

		p = new ProcessBuilder(o.getSmi(), "-L").start();
				try {
			p.waitFor();

			stdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line;

			while((line = stdOut.readLine()) != null) {
				gpus.add(line);
			} 

		} catch (InterruptedException e) {
			Logger.getAnonymousLogger().severe("Thread was interrupted while counting GPU's " + GpuLoad.class);
		} finally {
			stdOut.close();
		}
		return gpus.size();
	}

	/**
	 * Gets the gpu load.
	 *
	 * @param num the num
	 * @return the gpu load
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private int getGpuLoad(int num) throws IOException {

		int[] gpuLoad = new int[num];
		List<String> command = new ArrayList<String>();

		for (int gpu = 0; gpu < num; gpu++) {

			command.add(o.getSmi());
			command.add("-i");
		
			if (command.size() == 3) {
				command.remove(2);
			}

			command.add(String.valueOf(gpu));
			gpuLoad[gpu] = runSmiForLoad(command);
		}

		int totalGpuLoad = 0;

		for (int i = 0; i < gpuLoad.length; i++) {

			totalGpuLoad += gpuLoad[i]; 
		}

		return totalGpuLoad;		
	}

	/**
	 * Run smi for load.
	 *
	 * @param command the command
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private int runSmiForLoad(List<String> command) throws IOException {
		p = new ProcessBuilder(command).start();

		try {
			p.waitFor();

			LineNumberReader stdOut = new LineNumberReader(new InputStreamReader(p.getInputStream()));

			ArrayList<String> lines = new ArrayList<String>();

			String line;

			while ((line = stdOut.readLine()) != null ) {
				lines.add(line);
			}

			line = lines.get(8);

			String temp = line.trim().replaceAll("\\s+", "\t");
			String[] load = temp.split("\t");

			if (load[12].equalsIgnoreCase("0%")) {
				return 0;
			} 
			return 1;
		} catch (InterruptedException e) {
			Logger.getAnonymousLogger().severe("Something bad happend while waiting for nvidia-smi to complete!");
			Logger.getAnonymousLogger().severe("Exiting...");
			System.exit(1);
		} finally {			
			stdOut.close();
		}
		return 0;
	}

}
