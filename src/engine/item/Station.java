package engine.item;

public class Station extends InterestPoint {
	private String name;
	private String type;
	
	public Station(String name, String type) {
		super(name);
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return "Station de " + type + " : " + name;
	}

}
