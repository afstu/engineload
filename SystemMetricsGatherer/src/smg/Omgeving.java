package smg;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Omgeving {

	private String Clusternaam;
	private char Status;
	private String MetricURL;
	private int MetricPoort;
	private String MetricPad;
	private String ConfFile;

	private final Charset ENCODING = StandardCharsets.UTF_8;

	public Omgeving(String file) {
		setConfFile(file);
		getNodePortPathEnvFromConf();
	}

	public void getNodePortPathEnvFromConf() {

		List<String> lines = new ArrayList<String>();

		try {
			lines = leesConfFile(getConfFile());

			for (String line : lines) {

				if (line.startsWith("#")) {
					continue;
				}
				if (line.startsWith("NODE")) {
					String[] parts = line.split(":");
					setMetricURL(parts[1]);					
				}

				if (line.startsWith("PORT")) {
					String[] parts = line.split(":");
					setMetricPoort(Integer.parseInt(parts[1]));					
				}

				if (line.startsWith("PATH")) {
					String[] parts = line.split(":");
					setMetricPad(parts[1]);					
				}

				if (line.startsWith("CLUSTER")) {
					String[] parts = line.split(":");
					setClusternaam(parts[1]);					
				}

				if (line.startsWith("ENV")) {
					String[] parts = line.split(":");
					setStatus(parts[1].charAt(0));					
				}
			}
		} catch (IOException e) {
			Logger.getAnonymousLogger().severe("Something is wrong with the configuration file!");
			Logger.getAnonymousLogger().severe("Exiting...");
			System.exit(1);
		}
	}

	private List<String> leesConfFile(String file) throws IOException {
		Path path = Paths.get(file);

		return Files.readAllLines(path, ENCODING);
	}

	public String getClusternaam() {
		return Clusternaam;
	}

	public void setClusternaam(String clusternaam) {
		Clusternaam = clusternaam;
	}

	public char getStatus() {
		return Status;
	}

	public void setStatus(char status) {
		Status = status;
	}

	public String getMetricURL() {
		return MetricURL;
	}

	public void setMetricURL(String uRL) {
		MetricURL = uRL;
	}

	public int getMetricPoort() {
		return MetricPoort;
	}

	public void setMetricPoort(int poort) {
		MetricPoort = poort;
	}

	public String getMetricPad() {
		return MetricPad;
	}

	public void setMetricPad(String metricPad) {
		MetricPad = metricPad;
	}

	public String getConfFile() {
		return ConfFile;
	}

	public void setConfFile(String confFile) {
		ConfFile = confFile;
	}


}
