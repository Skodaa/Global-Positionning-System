package engine.item;

public abstract class ElementCarte {
	private String type;
	
	//Constructeur d'un Element Carte
	public ElementCarte(String type) {
		this.type=type;
	}
	
	//Methode SiChemin
	public boolean estChemin() {
		return type == "Chemin";
	}
	
	public boolean estPort() {
		return type == "Port";
	}
	
	public boolean estForet() {
		return type == "Foret";
	}
	
	public boolean estStationBus() {
		return type == "StationBus";
	}
	
	public boolean estEau() {
		return type == "Eau";
	}
	
	public boolean estStationTrain() {
		return type == "StationTrain";
	}
	
	public boolean estLieu() {
		return type == "lieux";
	}
	
	//Getters et Setters
	public String getType() {
		return type;
	}
}
