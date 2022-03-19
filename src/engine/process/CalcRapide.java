package engine.process;

import java.util.ArrayList;

import engine.item.Chemin;
import engine.item.ElementCarte;
import engine.item.Road;
import engine.map.Case;
import engine.map.Map;



public class CalcRapide {
	
	private Case depart;
	private Case arrive;
	private int count;
	private float tempCount;
	private int rep;
	private float tempFinal;
	private Map map;
	private ArrayList<Integer> pileCount;
	private ArrayList<Float> pileTempCount;
	private ArrayList<Case> chemin;
	private ArrayList<Case> cheminFinal;
	private boolean train;
	
	public CalcRapide(Map map,Case depart,Case arrive,boolean train) {
		this.map = map;
		this.depart = depart;
		this.arrive = arrive;
		this.rep = 0;
		this.count =0;
		this.tempCount = 0;
		this.tempFinal=0;
		this.pileCount = new ArrayList<Integer>();
		this.pileTempCount = new ArrayList<Float>();
		this.chemin = new ArrayList<Case>();
		this.cheminFinal = new ArrayList<Case>();
		this.train = train;
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
		//System.out.println(count);
		
		if(map.estIntersection(after,current,train)==1) {
			//System.out.println("intesection, on empile la valeur Count, et on la reinitialise");
			pileCount.add(count);
			pileTempCount.add(tempCount);
			count = 0;
			tempCount=0;
		}
		
		ArrayList<Case> adja = map.caseAdjacente(after,current,train);
		System.out.println(" case : " +current.getColonne()+"," +current.getLigne());
		for(int d=0;d<adja.size();d++) {
			//System.out.println(adja.get(d));
		}
		
		
			
		for(int i = 0;i<adja.size();i++) {
			if(adja.get(i)==arrive) {
				float temp = 0;
				for(int a = 0;a<pileTempCount.size();a++) {
					
					 temp += pileTempCount.get(a);
				}
				temp = temp + tempCount;
				System.out.println("Fin de la route, distance parcourue : "+ temp);
				if((temp < tempFinal)||(tempFinal==0)) {
					rep = chemin.size();
					tempFinal = temp;
					if(cheminFinal.size()!=0) {
						cheminFinal.clear();
					}
					for(int d = 0;d<chemin.size();d++) {
						cheminFinal.add(chemin.get(d));
					}
				}
			}
			else if((adja.get(i)!=after)){
				if(!chemin.contains(adja.get(i))) {
					
					float temp2 = 0;
					for(int a = 0;a<pileTempCount.size();a++) {
						
						 temp2 += pileTempCount.get(a);
					}
					temp2 = temp2 + tempCount;
					if((temp2 < tempFinal)||(tempFinal == 0)) {
						chemin.add(current);
						count = count + 1;
						tempCount = tempCount + calculeTemp(current);
						canContinue = 1;
						rep = testDist(adja.get(i),current);
					}
				}
			}
			if((i == adja.size()-1)&&(map.estIntersection(after,current,train)==1)){
				//System.out.println("On depile la pile, car la route n'a rien donné");
				for(int d=0;d<pileCount.get(pileCount.size()-1);d++){
					chemin.remove(chemin.size()-1);
				}
				pileCount.remove(pileCount.size()-1);
				pileTempCount.remove(pileTempCount.size()-1);
			}
		}
		if(canContinue == 0) {
			//System.out.println("Compteur : " +count);
			for(int d=0;d<count;d++){
				chemin.remove(chemin.size()-1);
			}
			count = 0;
			tempCount = 0;
			
		}
		
		return rep;
	}
	
	public float calculeTemp(Case emplacement) {
		float temp = 0;
		ArrayList<ElementCarte> it = emplacement.getInterest();
			for(ElementCarte ec : it) {
				if(ec.estChemin()==1) {
					Chemin ch = (Chemin)ec;
					if(ch.getName()=="road"){
						temp =0.1f/ ch.getSpeed();
					}
					if(ch.getName()=="rail"){
						temp =0.1f/ ch.getSpeed();
					}
				}
			}
		return temp*60;
	}
	
	public float getTemp() {
		return tempFinal;
	}
	
	public Case getDepart() {
		return depart;
	}
	
	public ArrayList<Case> getCheminFinal(){
		return cheminFinal;
	}
}
