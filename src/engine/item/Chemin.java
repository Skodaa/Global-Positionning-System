package engine.item;

public abstract class Chemin extends ElementCarte {
	private String name;
	
	public Chemin(String name) {
		super("chemin");
		this.name = name;
	}
	
	public abstract int getSpeed();
	
	public String getName() {
		return name;
	}
}
