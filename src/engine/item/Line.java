package engine.item;

import java.util.Collection;
import java.util.HashMap;

import engine.exception.NotFoundException;

public abstract class Line {

	private String number;
	private String confort;
	private int prix;
	private int vitesse;
	private String horaire; // temps entre chaque moyen de transport
	private HashMap<String, StationTrain> ligne;
	
	public Line(String number, String confort, int prix, int vitesse, String horaire) {
		this.number = number;
		this.confort = confort;
		this.prix = prix;
		this.vitesse = vitesse;
		this.horaire = horaire;
		this.ligne = new HashMap<String, StationTrain>();
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getConfort() {
		return confort;
	}
	public int getPrix() {
		return prix;
	}
	public int getVitesse() {
		return vitesse;
	}
	public String getHoraire() {
		return horaire;
	}
	public HashMap<String, StationTrain> getLigne() {
		return ligne;
	}
	
	public StationTrain getStation(String name) throws NotFoundException{
		StationTrain station = null;
		Collection<StationTrain> values = ligne.values();
		for(StationTrain st : values) {
			if(st.getName() == name) {
				station = st;
			}
		}
		if(station == null) {
			throw new NotFoundException();
		}
		else {
			return station;
		}
		
	}

	
}
