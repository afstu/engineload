package gpuload;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import utils.Utils;

public class gpuLoad implements Runnable {
	
	final static String WIN_SMI = "C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe";
	final static String LIN_SMI = "/usr/bin/nvidia-smi";
	
	Thread t;
	Utils u;
	String threadName;
	Boolean win = false;
	Boolean lin = false;
	
	public gpuLoad(String name) {
		
		threadName = name;
		
	}
		
	public void start() {
		
		u = new Utils();
		
		try {
		 	String osName= System.getProperty("os.name");
		 	
		 	if (osName.startsWith("Linux")) {
		 		lin = true;
		 	} else if (osName.startsWith("Windows")) {
		 		win = true;
		 	}	
		} catch (Exception e) {
			 System.out.println("Exception caught ="+e.getMessage());
		}
		
		if (lin && new File(LIN_SMI).isFile() ) {
			u.log("I see a Linux nvidia-smi!");
			runThread();
			}
		else if (win && new File(WIN_SMI).isFile() ) {
			u.log("I see a Windows nvidia-smi!");
			runThread();
			}
		else {
			return;
		}
	}

	@Override
	public void run() {
		try {
			
			runSmi();
			
			u.log("Ending load monitor thread..." + t.getName());
			t.join();
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
	
	private void runSmi() throws IOException {
		
		int num = getGpuNum();
		u.log("I see " + num  + " GPU(s)");
		
		getGpuLoad(num);
		
	}
	
	private int getGpuNum() throws IOException {
		
		Process p = null;
		ArrayList<String> gpus = new ArrayList<String>(); 		
		
		if (lin) {
			
			ArrayList<String> command = new ArrayList<String>();
			command.add(LIN_SMI);
			command.add("-L");
			
			p = new ProcessBuilder(command).start();
		}
		
		if (win) {
			
			ArrayList<String> command = new ArrayList<String>();
			command.add(WIN_SMI);
			command.add("-L");
			
			p = new ProcessBuilder(WIN_SMI).start();
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
	
	private void getGpuLoad(int num) {
		
	}
}
