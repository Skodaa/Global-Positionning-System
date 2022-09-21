package engine.map;

import java.util.ArrayList;

import engine.item.Chemin;
import engine.item.ElementCarte;

public class Case {
	
	private ArrayList<ElementCarte> elementsCase = new ArrayList<ElementCarte>();
	private int colonne;
	private int ligne;
	
	//Constructeur de la case
	public Case(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	//Méthode Contenant
	public boolean contientRoute() {
		for(ElementCarte ec :elementsCase) {
			if(contientChemin()) {
				Chemin ch = (Chemin) ec;
				if(ch.estRoute()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean contientRail() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estChemin()) {
				Chemin ch = (Chemin)ec;
				if(ch.getTypeChemin()=="Rail") {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean contientMaritime() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estChemin()) {
				Chemin ch = (Chemin)ec;
				if(ch.getTypeChemin()=="Maritime") {
					return true;
				}
			}
		}
		return false ;
	}
	
	public boolean contientPort() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estPort()) {
				return true;
			}
		}
		return false ;
	}
	
	public boolean contientForet() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estForet()) {
				return true;
			}
		}
		return false ;
	}
	
	public boolean contientLieu() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estLieu()) {
				return true;
			}
		}
		return false ;
	}
	
	public boolean contientEau() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estEau()) {
				return true;
			}
		}
		return false ;
	}
	
	public boolean contientStationTrain() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estStationTrain()) {
				return true;
			}
		}
		return false ;
	}
	
	public boolean contientChemin() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estChemin()) {
				return true;
			}
		}
		return false ;
	}
	
	public boolean contientBus() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estChemin()) {
				Chemin ch = (Chemin)ec;
				if(ch.getTypeChemin()=="Bus") {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean contientStationBus() {
		for(ElementCarte ec : elementsCase) {
			if(ec.estStationBus()) {
				return true;
			}
		}
		return false ;
	}
	
	//Getters et Setters
	public int getColonne() {
		return colonne;
	}
	
	public int getLigne() {
		return ligne;
	}
	
	public ArrayList<ElementCarte> getElementsCase(){
		return elementsCase;
	}
	
	public void ajouterElement(ElementCarte ec) {
		elementsCase.add(ec);
	}
	
}
