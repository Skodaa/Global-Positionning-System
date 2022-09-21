package engine.process;

import java.util.ArrayList;

import engine.item.Chemin;
import engine.item.ElementCarte;
import engine.map.Carte;
import engine.map.Case;


public class CalculeTrajet {
	
	//Attribut/option de la class
	private Case depart;
	private Case arrive;
	//Compteurs
	private int compteur;
	private float compteurTemps;
	private float compteurComfort;
	//resultats finaux
	private int resultat;
	private float tempsFinal;
	private float comfortFinal;
	//piles Compteurs
	private ArrayList<Integer> pileCompteur;
	private ArrayList<Float> pileTempCompteur;
	private ArrayList<Float> pileComfort;
	//paramètres trajets
	private boolean train;
	private boolean boat;
	private boolean bus;
	//piles contenant le chemin en cours de calcule/calculé
	private ArrayList<Case> chemin;
	private ArrayList<Case> cheminFinal;
	
	//private int jeSuisDansLeBus;
	private ArrayList<Integer> pileBus;
	private ArrayList<Integer> pileBusFinal;
	
	private CaseAdjacentManager CTM;
	
	//Constructeur
	public CalculeTrajet(Carte carte,Case depart,Case arrive,boolean train,boolean boat,boolean bus) {
		this.depart = depart;
		this.arrive = arrive;
		
		this.compteur =0;
		this.compteurTemps = 0;
		this.compteurComfort = 0;

		this.tempsFinal=0;
		this.comfortFinal=0;
		this.resultat = 0;

		this.pileCompteur = new ArrayList<Integer>();
		this.pileTempCompteur = new ArrayList<Float>();
		this.pileComfort = new ArrayList<Float>();

		this.chemin = new ArrayList<Case>();
		this.cheminFinal = new ArrayList<Case>();

		this.train = train;
		this.boat = boat;
		this.bus = bus;
		
		this.pileBus = new ArrayList<Integer>();
		this.pileBusFinal = new ArrayList<Integer>();
		
		this.CTM = new CaseAdjacentManager(carte);
	}
	
	public int calculerChemin(Case caseActuel,Case casePrecedent, String type, int jeSuisDansLeBus, boolean lieupass ) {
		
		//par default, on ne peu pas continuer.
		int canContinue = 0;
		
		//on test dans le cas d'un trajet touristique si le caseActuel contient un lieu touristique
		if(type=="Touristique"&&caseActuel.contientLieu()) {
			lieupass = true;
		}

		//on verifie si la case actuel est une intersection, si oui on ajoute les compteurs au piles correspondantes. On reinitialise ensuite les compteurs.
		if((CTM.estIntersection(casePrecedent,caseActuel,train,boat,bus,jeSuisDansLeBus))) {
			pileCompteur.add(compteur);
			pileTempCompteur.add(compteurTemps);
			pileComfort.add(compteurComfort);
			compteur = 0;
			compteurTemps=0;
			compteurComfort=0;
		}
		
		//on récupère les cases adjacentes en fonctions des paramètres
		ArrayList<Case> adja = CTM.caseAdjacente(casePrecedent,caseActuel,train,boat,bus,jeSuisDansLeBus);
		
		if((caseActuel.contientStationBus()&&!passeBus(adja, casePrecedent))&&bus) {
			jeSuisDansLeBus = 0;
		}
						
			
		for(int i = 0;i<adja.size();i++) {
			//on test si on arrive dans la case d'arrive, et si nous ne somme pas dans le bus
			if(adja.get(i)==arrive&&jeSuisDansLeBus==0) {
				int temp = 0;
				float temp2 = 0;
				int temp3 = 0;
				
				for(int a = 0;a<pileTempCompteur.size();a++) {
					 temp += pileCompteur.get(a);
					 temp2 += pileTempCompteur.get(a);
					 temp3 += pileComfort.get(a);
				}
					
				temp = temp + compteur + 1;
				temp2 = temp2 + compteurTemps;
				temp3 += compteurComfort;
				//en fonction du type de trajet, la condition de validité change	
				if(type=="Touristique") {
					if(((temp2 < tempsFinal)||(tempsFinal==0))&&lieupass==true) {
						exportInFinal(caseActuel, temp2,temp3);
					}
				}
				
				else if(type=="Rapide") {
					if((temp2 < tempsFinal)||(tempsFinal==0)) {
						exportInFinal(caseActuel, temp2,temp3);
					}
				}
				
				else if(type =="Distance") {
					if((temp < resultat)||(resultat==0)) {
						exportInFinal(caseActuel, temp2,temp3);
					}
				}
				
				else if(type=="Comfort") {
					if((temp3 < comfortFinal)||(comfortFinal==0)) {
						exportInFinal(caseActuel, temp2,temp3);
					}
				}				
			}
			// on regarde pour chaque case adjacente si elle n'est pas la même que la case d'où l'on vient
			else if(adja.get(i)!=casePrecedent){
				//si l'on a 2 fois la même case dans la liste adja, on modifie la varibale je suis dans le bus
				if(!chemin.contains(adja.get(i))) {
						
					if(i!=adja.size()-1) {
						if(adja.get(i)==adja.get(i+1)) {
							jeSuisDansLeBus = 1;
						}
					}
					if(i!=0){
						if(adja.get(i)==adja.get(i-1)) {
							jeSuisDansLeBus = 0;
						}
					}
						
					//en fonction du type de trajet, la condition de validité change
					// on incremente les compteurs et on passe a la case suivante du chemin testé.
					if(type=="Rapide"||type=="Touristique") {
						float temp = 0;							
						for(int a = 0;a<pileTempCompteur.size();a++) {
							 temp += pileTempCompteur.get(a);
						}
						temp = temp + compteurTemps;
						if((temp < tempsFinal)||(tempsFinal == 0)) {
							processCase(caseActuel, jeSuisDansLeBus);
							canContinue = 1;
							resultat = calculerChemin(adja.get(i),caseActuel,type,jeSuisDansLeBus,lieupass);
						}
					}
					else if(type=="Distance") {
						int temp = 0;
						for(int a = 0;a<pileTempCompteur.size();a++) {
							 temp += pileCompteur.get(a);
						}
						temp = temp + compteur + 1;
						if((temp < resultat)||(resultat == 0)) {
							processCase(caseActuel, jeSuisDansLeBus);
							canContinue = 1;
							resultat = calculerChemin(adja.get(i),caseActuel,type,jeSuisDansLeBus,lieupass);
						}
					}
					else if(type=="Comfort") {
						float temp = 0;
						for(int a = 0;a<pileComfort.size();a++) {
							 temp += pileComfort.get(a);
						}
						temp += compteurComfort;
						if((temp < comfortFinal)||(comfortFinal == 0)) {
							processCase(caseActuel, jeSuisDansLeBus);
							canContinue = 1;
							resultat = calculerChemin(adja.get(i),caseActuel,type,jeSuisDansLeBus,lieupass);
						}
					}
				}
			}
			if((i == adja.size()-1)&&(CTM.estIntersection(casePrecedent,caseActuel,train,boat,bus,jeSuisDansLeBus))){
				
				for(int elem : pileCompteur){
					System.out.print(elem+" ");
				}
				int test2 = pileCompteur.size();
				
				for(int d=0;d<pileCompteur.get(test2-1);d++){
					chemin.remove(chemin.size()-1);
					pileBus.remove(pileBus.size()-1);
				}
					
				
				pileCompteur.remove(pileCompteur.size()-1);
				pileTempCompteur.remove(pileTempCompteur.size()-1);
				pileComfort.remove(pileComfort.size()-1);

				
			}
		}
		//si l'on ne peu pas continuer le chemin, alors on supprime la partie de la route inacsessible grace a la valeur du compteur
		if(canContinue == 0) {
			System.out.println("Je ne peu plus continuer");
			for(int d=0;d<compteur;d++){
				chemin.remove(chemin.size()-1);
				pileBus.remove(pileBus.size()-1);
			}
			compteur = 0;
			compteurTemps = 0;
			compteurComfort = 0;
			
		}
		
		return resultat;
	}
	
	//methode qui calcule le temps de parcours d'une case, via la vitesse du moyen de transport utilisé
	public float calculeTemp(Case emplacement,int jsdlb) {
		float temp = 0;
		ArrayList<ElementCarte> it = emplacement.getElementsCase();
		if(jsdlb == 0) {
			for(ElementCarte ec : it) {
				if(ec.estChemin()) {
				
					Chemin ch = (Chemin)ec;
					
					if(ch.getTypeChemin()=="Rail"){
						temp =0.1f/ ch.getVitesseMoyenne();
					}
					else if(ch.getTypeChemin()=="Route"){
						temp =0.1f/ ch.getVitesseMoyenne();
					}
					if(ch.getTypeChemin()=="Maritime"){
						temp =0.1f/ ch.getVitesseMoyenne();
					}
						
				}
			}
		}
		else {
			for(ElementCarte ec : it) {
				if(ec.estChemin()) {
					Chemin ch = (Chemin)ec;
					if(ch.getTypeChemin()=="Bus"){
						temp =0.1f/ ch.getVitesseMoyenne();
						System.out.println("vavaou");
					}
				}
			}
		}
		return temp*60;
	}
	
	//methode pour récupérer la bonne valeur de confort contenue dans la case
	public int choiceComfort(Case emplacement,int jsdlb) {
		ArrayList<ElementCarte> it = emplacement.getElementsCase();
		int val = 0;
		if(jsdlb == 0) {
			for(ElementCarte ec : it) {
				if(ec.estChemin()) {
				
					Chemin ch = (Chemin)ec;
					
					if(ch.getTypeChemin()=="Rail"){
						val = ch.getComfort();
					}
					else if(ch.getTypeChemin()=="Route"){
						val = ch.getComfort();
					}
					if(ch.getTypeChemin()=="Maritime"){
						val = ch.getComfort();
					}
						
				}
			}
		}
		else {
			for(ElementCarte ec : it) {
				if(ec.estChemin()) {
					Chemin ch = (Chemin)ec;
					if(ch.getTypeChemin()=="Bus"){
						val = ch.getComfort();
					}
				}
			}
		}
		return val;
	}
	
	//methode qui verifie si les cases adjacentes contiennent au moins un chemin bus possible
	public boolean passeBus(ArrayList<Case> cases, Case casePrecedent ) {
		boolean po = false;
		for(Case elt : cases) {
			if(elt.contientBus()&&bus) {
				if(elt!=casePrecedent) {
					po = true;
				}
			}
		}
		return po;
	}
	
	// permet la sauvegarde des chemin et du compteur lorsque l'on arrive sur la case d'arrivée
	public void exportInFinal(Case caseActuel, float temp2,int temp3) {
		resultat = chemin.size();
		tempsFinal = temp2;
		comfortFinal = temp3;

	
		if(cheminFinal.size()!=0) {
			cheminFinal.clear();
			pileBusFinal.clear();
		}
		for(int d = 0;d<chemin.size();d++) {
			cheminFinal.add(chemin.get(d));
			pileBusFinal.add(pileBus.get(d));
		}
		cheminFinal.add(caseActuel);
		cheminFinal.add(caseActuel);
		pileBusFinal.add(0);
		pileBusFinal.add(0);
	}
	
	// methode pour incrementer les compteur, et ajouter la case testé au chemin
	public void processCase(Case caseActuel,int jeSuisDansLeBus) {
		chemin.add(caseActuel);
		pileBus.add(jeSuisDansLeBus);
		compteur = compteur + 1;
		compteurComfort += choiceComfort(chemin.get(chemin.size()-1), pileBus.get(pileBus.size()-1));
		compteurTemps = compteurTemps + calculeTemp(chemin.get(chemin.size()-1),pileBus.get(pileBus.size()-1));
		
	}
	
	//getters/setters
	public float getTemp() {
		return tempsFinal;
	}
	
	public Case getDepart() {
		return depart;
	}
	
	public ArrayList<Case> getCheminFinal(){
		return cheminFinal;
	}
	
	public ArrayList<Integer> getBusFinal(){
		return pileBusFinal;
	}
	
}
