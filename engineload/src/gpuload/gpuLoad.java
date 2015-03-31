package gpuload;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

import utils.Utils;

public class gpuLoad implements Runnable {
	
	Thread t;
	Utils u;
	String threadName;
	
	public gpuLoad(String name) {
		
		threadName = name;	
	}
		
	public void start() {
		
		u = new Utils();
		
	}

	@Override
	public void run() {
		
		try {
			
			int loadCache[] = new int[12];
			int numGpu = getGpuNum();

			while (true) {
				
				for (int i = 0; i < 12; i++) {
					
					loadCache[i] = getGpuLoad(numGpu);;
					
					Thread.sleep(5000);
				}
				
				int max = 0;
				
				for (int i = 0 ; i < loadCache.length ; i++) {
					
					if ( max < loadCache[i]) {
						max = loadCache[i];
					}
				}
				
				u.log("The GPU load is : " + max  + " GPU(s) active");
			}
			
//			u.log("Ending load monitor thread..." + t.getName());
//			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void runThread() {
		if (t == null) {
			t = new Thread(this, threadName);
			u.log("Starting load monitor thread..." + t.getName());
			t.start();
		}
	}
	
	private int getGpuNum() throws IOException {
		
		Process p = null;
		ArrayList<String> gpus = new ArrayList<String>(); 		
		
		if (u.getLin()) {
			p = new ProcessBuilder(u.getLIN_SMI_NUMGPU()).start();
		}
		
		if (u.getWin()) {
			p = new ProcessBuilder(u.getWIN_SMI_LOADGPU()).start();
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
		
		Process p = null;
		int[] gpuLoad = new int[num];
		
		for (int gpu = 0; gpu < num; gpu++) {
		
			if (u.getLin()) {
				
				ArrayList<String> command = new ArrayList<String>();
				command.add(u.getLIN_SMI_LOADGPU());
				command.add(String.valueOf(gpu));
				
				p = new ProcessBuilder(command).start();
			}
			
			if (u.getWin()) {
				
				ArrayList<String> command = new ArrayList<String>();
				command.add(u.getWIN_SMI_LOADGPU());
				command.add(String.valueOf(gpu));
								
				p = new ProcessBuilder(command).start();
			}
			
			try {
				
				int code = p.waitFor();
				
				LineNumberReader stdOut = new LineNumberReader(new InputStreamReader(p.getInputStream()));
				BufferedReader stdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));

				ArrayList<String> lines = new ArrayList<String>();
				String line;
					
				while ((line = stdOut.readLine()) != null ) {
					
					lines.add(line);
				}
					
				line = lines.get(8);
					
				String temp = line.trim().replaceAll("\\s+", "\t");
				String[] load = temp.split("\t");
				
				if (!load[12].equalsIgnoreCase("0%")) {
					gpuLoad[gpu] = 1;
				}
				
				stdErr.close();						
				stdOut.close();
				
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		int totalGpuLoad = 0;
		
		for (int i = 0; i < gpuLoad.length; i++) {

			totalGpuLoad += gpuLoad[i]; 
		}
		
		return totalGpuLoad ;		
	}
}
