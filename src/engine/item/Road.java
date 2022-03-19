package engine.item;

public class Road extends Chemin {
	private String typeChemin;
	private int speedLimit;
	
	public Road(String type) {
		super("road");
		this.typeChemin = type;
		if(type == "ville") {
			this.speedLimit = 50;
		}
		else if(type == "campagne") {
			this.speedLimit = 300;
		}
	}
	
	public String getTypeChemin() {
		return typeChemin;
	}
	
	public int getSpeed() {
		return speedLimit;
	}
	
}
