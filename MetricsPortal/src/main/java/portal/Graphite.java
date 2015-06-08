package portal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Graphite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String GraphiteUrl;
	
	public Graphite() {
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getGraphiteUrl() {
		return GraphiteUrl;
	}
	public void setGraphiteUrl(String graphiteUrl) {
		this.GraphiteUrl = graphiteUrl;
	}

	 public String getEditLink() {
	        return "<a href='/graphite/edit/" + this.GraphiteUrl + "'>Edit</a>";
	    }
	 
	    public String getDeleteLink() {
	        return "<a href='/graphite/delete/" + this.GraphiteUrl + "'>Delete</a>";
	    }
	
}
