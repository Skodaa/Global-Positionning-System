package engine.item;

public class StationTrain extends ElementCarte {
	private String name;
	private int x;
	private int y;
	
	public StationTrain(String name,int x,int y) {
		super("stationTrain");
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "Station " + " : " + name;
	}

}
