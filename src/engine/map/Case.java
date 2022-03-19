package engine.map;

import java.util.ArrayList;

import engine.item.Chemin;
import engine.item.ElementCarte;

public class Case {
	private int num;
	private ArrayList<ElementCarte> it = new ArrayList<ElementCarte>();
	private int colonne;
	private int ligne;
	
	public Case(int colonne, int ligne) {
		this.num = 0;
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	public int getNum() {
		return num;
	}
	
	public int getColonne() {
		return colonne;
	}
	
	public int getLigne() {
		return ligne;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public ArrayList<ElementCarte> getInterest(){
		return it;
	}
	
	public int haveRoad() {
		for(ElementCarte i : it) {
			if(i.estChemin()==1) {
				Chemin ch = (Chemin)i;
				if(ch.getName()=="road") {
					return 1;
				}
			}
		}
		return 0 ;
	}
	
	public int haveStationTrain() {
		for(ElementCarte i : it) {
			if(i.getType()=="stationTrain") {
				return 1;
			}
		}
		return 0 ;
	}
	
	public int haveChemin() {
		for(ElementCarte i : it) {
			if(i.estChemin()==1) {
				return 1;
			}
		}
		return 0 ;
	}
	
	public int haveRail() {
		for(ElementCarte i : it) {
			if(i.estChemin()==1) {
				Chemin ch = (Chemin)i;
				if(ch.getName()=="rail") {
					return 1;
				}
			}
		}
		return 0 ;
	}
	
	public int haveWater() {
		for(ElementCarte i : it) {
			if(i.estEau()==1) {
				return 1;
			}
		}
		return 0 ;
	}
	
	
	
	public void appendNewIP(ElementCarte ip) {
		it.add(ip);
	}
}
