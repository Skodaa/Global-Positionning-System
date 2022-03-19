package engine.map;

import java.util.ArrayList;

import engine.item.InterestPoint;

public class Case {
	private int num;
	private ArrayList<InterestPoint> it = new ArrayList<InterestPoint>();
	private int colonne;
	private int ligne;
	
	public Case(int colonne, int ligne) {
		this.num = 0;
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	/*public Case(int num,int colonne,) {
		this.num = num;
		this.colonne = colonne;
		this.ligne = ligne;
	}*/
	
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
	
	public ArrayList<InterestPoint> getInterest(){
		return it;
	}
	
	public int haveRoad() {
		for(InterestPoint i : it) {
			if(i.getName()=="road") {
				return 1;
			}
		}
		return 0 ;
	}
	
	public int haveWater() {
		for(InterestPoint i : it) {
			if(i.getName()=="water") {
				return 1;
			}
		}
		return 0 ;
	}
	
	public void appendNewIP(InterestPoint ip) {
		it.add(ip);
	}
}
