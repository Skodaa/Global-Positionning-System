package engine.item;

public class Rail extends Chemin {
	
	private int vitesseMoyenne;
	
	public Rail() {
		super("rail");
		this.vitesseMoyenne = 100;
	}
	
	public int getSpeed() {
		return vitesseMoyenne;
	}
}
