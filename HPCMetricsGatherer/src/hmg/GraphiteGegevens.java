package hmg;

public class GraphiteGegevens {
	
	private String GraphiteUrl;
	private String GraphitePad;
	private int GraphitePoort;
	
	public GraphiteGegevens() {
		
	}

	public int getGraphitePoort() {
		return GraphitePoort;
	}

	public void setGraphitePoort(int graphitePoort) {
		GraphitePoort = graphitePoort;
	}
	
	public String getGraphiteUrl() {
		return GraphiteUrl;
	}

	public void setGraphiteUrl(String graphiteUrl) {
		GraphiteUrl = graphiteUrl;
	}

	public String getGraphitePad() {
		return GraphitePad;
	}

	public void setGraphitePad(String graphitePad) {
		GraphitePad = graphitePad;
	}
	
}
