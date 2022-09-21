package engine.process;

import java.util.ArrayList;

import engine.exception.TrajetNotFoundException;
import engine.exception.WrongParametersException;
import engine.map.Carte;
import engine.map.Case;

public class CalculeTrajetManager {
private Carte carte;
	
	private int distance;
	private CalculeTrajet ct;
	private float temp;
	
	//Le chemins resultant du calcul
	private ArrayList<Case> chemin;
	private ArrayList<Integer> busf;
	
	//Les cases departs et arrivé
	private Case start;
	private Case finish;
	
	public CalculeTrajetManager(Carte carte) {
		this.carte = carte;
		start = null;
		finish = null;
		distance = 0;
		temp = 0;
	}
	
	
	//pour reste les points de depart et d'arrivé
	public void resetStartEnd() {
		start = null;
		finish = null;
	}
	
	//methode qui lance le calcul
	public void trajet(boolean train,boolean boat,boolean bus,String type) throws WrongParametersException, TrajetNotFoundException{
		if(start != null && finish!= null) {
			ct = new CalculeTrajet(carte,carte.getCase(start.getColonne(), start.getLigne()),carte.getCase(finish.getColonne(), finish.getLigne()),train,boat,bus);
			distance = ct.calculerChemin(ct.getDepart(),null,type,0,false);
			temp = ct.getTemp();
			chemin = ct.getCheminFinal();
			busf = ct.getBusFinal();
			if(chemin.size()==0) {
				throw new TrajetNotFoundException();
			}
		}else {
			throw new WrongParametersException("Les paramètres de départ et/ou d'arriver son érronés");
		}
	}
	
	
	//getters setters
	public int getDistance() {
		return distance;
	}
	
	public float getTemp() {
		return temp;
	}
	
	public ArrayList<Case> getChemin(){
		return chemin;
	}
	public ArrayList<Integer> getBus(){
		return busf;
	}
	
	
	public Case getStart() {
		return start;
	}

	public void setStart(Case start) {
		this.start = start;
	}

	public Case getFinish() {
		return finish;
	}

	public void setFinish(Case finish) {
		this.finish = finish;
	}


}
