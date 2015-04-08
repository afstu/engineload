package load;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import utils.Utils;

public class GpuLoad extends Load {

	public GpuLoad(String name, Utils utils) {
		super(name, utils);
	}

	@Override
	public void getLoad() {

		try {

			int loadCache[] = new int[12];
			int numGpu = getGpuNum();

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
				}

				buildMetricAndWrite(max, "GPU");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getGpuNum() throws IOException {

		Process p = null;
		ArrayList<String> gpus = new ArrayList<String>(); 		

		if (u.getLin()) {
			p = new ProcessBuilder(u.getLIN_SMI_NUMGPU()).start();
		}

		if (u.getWin()) {
			p = new ProcessBuilder(u.getWIN_SMI_NUMGPU()).start();
		}

		try {
			int code = p.waitFor();

			BufferedReader stdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			if (code == 0) {
				String line;
				while((line = stdOut.readLine()) != null) {
					gpus.add(line);
				}

			} else {
				u.log(stdErr);
			}

			stdErr.close();						
			stdOut.close();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gpus.size();
	}

	private int getGpuLoad(int num) throws IOException {

		int[] gpuLoad = new int[num];
		List<String> command = new ArrayList<String>();

		for (int gpu = 0; gpu < num; gpu++) {

			if (u.getLin()) {
				command = u.getLIN_SMI_LOADGPU();
			}

			if (u.getWin()) {
				command = u.getWIN_SMI_LOADGPU();
			}

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
		return totalGpuLoad ;		
	}

	private int runSmiForLoad(List<String> command) throws IOException {
		Process p = new ProcessBuilder(command).start();

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			u.log("Something bad happend while waiting for nvidia-smi to complete!");
			u.log("Exiting...");
			System.exit(1);
		}

		LineNumberReader stdOut = new LineNumberReader(new InputStreamReader(p.getInputStream()));

		ArrayList<String> lines = new ArrayList<String>();

		String line;

		while ((line = stdOut.readLine()) != null ) {
			lines.add(line);
		}

		stdOut.close();

		line = lines.get(8);

		String temp = line.trim().replaceAll("\\s+", "\t");
		String[] load = temp.split("\t");

		if (load[12].equalsIgnoreCase("0%")) {
			return 0;
		} 
		return 1;
	}
}