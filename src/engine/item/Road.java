package engine.item;

public class Road extends InterestPoint {
	private String type;
	private int speedLimit;
	
	public Road(String type) {
		super("road");
		this.type = type;
		this.speedLimit = 50;
	}
	
	public String getType() {
		return type;
	}
	
	public int getSpeedLimit() {
		return speedLimit;
	}
	
}
