package engine.process;

import java.util.ArrayList;

import engine.map.Case;
import engine.map.Map;

public class CalculeDistance {
	
	private Case depart;
	private Case arrive;
	private int count;
	private int rep;
	private Map map;
	private ArrayList<Integer> pileCount;
	private ArrayList<Case> chemin;
	private ArrayList<Case> cheminFinal;
	
	public CalculeDistance(Map map,Case depart,Case arrive) {
		this.map = map;
		this.depart = depart;
		this.arrive = arrive;
		this.rep = 0;
		this.count =0;
		this.pileCount = new ArrayList<Integer>();
		this.chemin = new ArrayList<Case>();
		this.cheminFinal = new ArrayList<Case>();
	}
	
	public int testDist(Case current,Case after) {
		int xi = current.getColonne();
		int yi = current.getLigne();
		
		int xa = arrive.getColonne();
		int ya = arrive.getLigne();
		
		int canContinue = 0;
		
		int sousx = xa -xi;
		sousx = Math.abs(sousx);
		int sousy = ya -yi;
		sousy = Math.abs(sousy);
		System.out.println(count);
		
		if(map.estIntersection(current)==1) {
			System.out.println("intesection, on empile la valeur Count, et on la reinitialise");
			pileCount.add(count);
			count = 0;
			affichePile();
		}
		
		ArrayList<Case> adja = map.caseAdjacente(current);
		System.out.println(" case : " +current.getColonne()+"," +current.getLigne());
		for(int d=0;d<adja.size();d++) {
			System.out.println(adja.get(d));
		}
		
		
			
		for(int i = 0;i<adja.size();i++) {
			if(adja.get(i)==arrive) {
				int temp = 0;
				for(int a = 0;a<pileCount.size();a++) {
					
					 temp += pileCount.get(a);
				}
				temp = temp + count + 1;
				System.out.println("Fin de la route, distance parcourue : "+ temp);
				if((temp < rep)||(rep==0)) {
					rep = temp;
					if(cheminFinal.size()!=0) {
						cheminFinal.clear();
					}
					for(int d = 0;d<chemin.size();d++) {
						cheminFinal.add(chemin.get(d));
					}
				}
			}
			else if(/*((Math.abs((xa - adja.get(i).getColonne()))<sousx)||(Math.abs((ya - adja.get(i).getLigne()))<sousy))&&*/(adja.get(i)!=after)){
				if(!chemin.contains(adja.get(i))) {
					int temp2 = 0;
					for(int a = 0;a<pileCount.size();a++) {
						
						 temp2 += pileCount.get(a);
					}
					temp2 = temp2 + count + 1;
					if((temp2 < rep)||(rep == 0)) {
						chemin.add(current);
						count = count + 1;
						canContinue = 1;
						rep = testDist(adja.get(i),current);
					}
				}
			}
			if((i == adja.size()-1)&&(map.estIntersection(current)==1)){
				System.out.println("On depile la pile, car la route n'a rien donné");
				for(int d=0;d<pileCount.get(pileCount.size()-1);d++){
					chemin.remove(chemin.size()-1);
				}
				pileCount.remove(pileCount.size()-1);
				affichePile();
			}
		}
		if(canContinue == 0) {
			System.out.println("Compteur : " +count);
			for(int d=0;d<count;d++){
				chemin.remove(chemin.size()-1);
			}
			count = 0;
			
		}
		
		return rep;
	}
	
	public void affichePile() {
		System.out.println("Contenue de la pile :");
		for(int i = 0; i < pileCount.size();i++) {
			System.out.println(pileCount.get(i));
		}
	}
	
	public Case getDepart() {
		return depart;
	}
	
	public ArrayList<Case> getCheminFinal(){
		return cheminFinal;
	}
	
}
