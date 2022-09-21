package engine.item;

public class Port extends ElementCarte {
	private String name;
	private int x;
	private int y;
	
	public Port(String name,int x,int y) {
		super("Port");
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "Port " + " : " + name;
	}

}