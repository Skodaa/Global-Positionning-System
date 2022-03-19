package engine.item;

public abstract class ElementCarte {
	private String typeElement;
	
	public ElementCarte(String type) {
		this.typeElement = type;
	}
	
	public String getType() {
		return typeElement;
	}
	
	public int estChemin() {
		if(getType()=="chemin") {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public int estEau() {
		if(getType()=="water") {
			return 1;
		}
		else {
			return 0;
		}
	}
}
