package engine.item;

public class StationBus extends ElementCarte {
	//Attribut de class
	private String name;
	private int x;
	private int y;

	public StationBus(String name,int x, int y) {
		super("StationBus");
		this.name = name;
		this.x = x;
		this.y = y;
	}
}
