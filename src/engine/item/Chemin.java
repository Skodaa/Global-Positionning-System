package engine.item;

public abstract class Chemin extends ElementCarte {
	
	//attribut de la classe
	private int vitesseMoyenne;
	private String typeChemin;
	private int comfort;
	
	//Constructeur de la classe
	public Chemin(String typeChemin ,int vitesseMoyenne,int comfort) {
		super("Chemin");
		this.typeChemin = typeChemin;
		this.vitesseMoyenne = vitesseMoyenne;
		this.comfort = comfort;
	}
	
	//Methode test
	public boolean estRoute() {
		return typeChemin == "Route";
	}
	
	//Getters et Setters
	public int getVitesseMoyenne(){
		return vitesseMoyenne;
	}
	
	public String getTypeChemin() {
		return typeChemin;
	}
	
	public int getComfort() {
		return comfort;
	}
	
}
