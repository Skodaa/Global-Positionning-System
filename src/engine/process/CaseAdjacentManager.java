package engine.process;

import java.util.ArrayList;

import engine.map.Carte;
import engine.map.Case;

public class CaseAdjacentManager {
	
	private Carte carte;
	
	public CaseAdjacentManager(Carte carte) {
		this.carte = carte;
	}
	
	//methode qui determine si la case actuel est une intersection
	public boolean estIntersection(Case casePrecedent,Case caseActuel,boolean train,boolean boat,boolean bus,int jsdlb) {
		ArrayList<Case> adja = caseAdjacente(casePrecedent,caseActuel,train,boat,bus,jsdlb);
		if(adja.size()>2) {
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Case> caseAdjacente(Case before,Case caseuh, boolean train,boolean boat,boolean bus,int jsdlb){
		ArrayList<Case> route = new ArrayList<Case>();
		if(train&&boat&&bus) {
			int i = 0;
			int j = -1;
			
			if(testCaseTetBetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseTetBetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = -1;
			j = 0;
			if(testCaseTetBetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseTetBetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			
			i = 1;
			j = 0;
			if(testCaseTetBetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseTetBetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = 0;
			j = 1;
			if(testCaseTetBetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseTetBetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
		}
		else if(train&&boat&&!bus) {
			int i = 0;
			int j = -1;
			
			if(testCaseTetB(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}

			i = -1;
			j = 0;
			if(testCaseTetB(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}

			i = 1;
			j = 0;
			if(testCaseTetB(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			i = 0;
			j = 1;
			if(testCaseTetB(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
		}
		else if(train&&!boat&&bus) {
			int i = 0;
			int j = -1;
			
			if(testCaseTetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseTetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = -1;
			j = 0;
			if(testCaseTetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseTetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			
			i = 1;
			j = 0;
			if(testCaseTetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseTetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = 0;
			j = 1;
			if(testCaseTetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseTetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
		}
		else if(train&&!boat&&!bus) {
			int i = 0;
			int j = -1;
			
			if(testCaseTrain(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			i = -1;
			j = 0;
			if(testCaseTrain(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = 1;
			j = 0;
			if(testCaseTrain(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			i = 0;
			j = 1;
			if(testCaseTrain(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
		}
		else if(!train&&boat&&bus) {
			int i = 0;
			int j = -1;
			
			if(testCaseBetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseBetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = -1;
			j = 0;
			if(testCaseBetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseBetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			
			i = 1;
			j = 0;
			if(testCaseBetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseBetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = 0;
			j = 1;
			if(testCaseBetBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseBetBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
		}
		else if(!train&&boat&&!bus) {
			int i = 0;
			int j = -1;
			
			if(testCaseBoat(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			i = -1;
			j = 0;
			if(testCaseBoat(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = 1;
			j = 0;
			if(testCaseBoat(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			i = 0;
			j = 1;
			if(testCaseBoat(i,j,before,caseuh)) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
		}
		else if(!train && !boat && bus) {
			int i = 0;
			int j = -1;
			
			if(testCaseBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = -1;
			j = 0;
			if(testCaseBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			
			i = 1;
			j = 0;
			if(testCaseBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
			
			i = 0;
			j = 1;
			if(testCaseBus(i,j,before,caseuh,jsdlb)==1) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			else if(testCaseBus(i,j,before,caseuh,jsdlb)==2) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			
		}
		else if(!train && !boat && !bus) {
			 int i = 0;
			 int j = -1;
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			 i = -1;
			 j = 0;
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			 i = 1;
			 j = 0;
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
			 i = 0;
			 j = 1;
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
				route.add(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j));
			}
		}
		
		return route;
	}
	
	public boolean testCaseTrain(int i, int j,Case before,Case caseuh) {
		if (caseuh.contientRail()&&!caseuh.contientRoute()) {
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(caseuh.contientRoute()&&!caseuh.contientRail()) {
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(caseuh.contientRoute()&&caseuh.contientRail()) {
			if(caseuh.contientStationTrain()) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(before.contientRail()&&!before.contientRoute()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()) {
						return true;
					}
					else {
						return false;
					}
				}
				else if(!before.contientRail()&&before.contientRoute()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		else {
			return false;
		}
	}
	
	public boolean testCaseBoat(int i, int j,Case before,Case caseuh) {
		if (caseuh.contientMaritime()&&!caseuh.contientRoute()) {
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(caseuh.contientRoute()&&!caseuh.contientMaritime()) {
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(caseuh.contientRoute()&&caseuh.contientMaritime()) {
			if(caseuh.contientPort()) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(before.contientMaritime()&&!before.contientRoute()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
						return true;
					}
					else {
						return false;
					}
				}
				else if(!before.contientMaritime()&&before.contientRoute()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		else {
			return false;
		}
	}
	
	public int testCaseBus(int i, int j,Case before,Case caseuh,int jsdlb) {
		//System.out.println(jsdlb);
		if(caseuh.contientStationBus()) {
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
				return 2;
			}
			else if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&!carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
				return 1;
			}
			else if(!carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()){
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			if(jsdlb==1) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
					return 1;
				}
				else {
					return 0;
				}
			}
			else if(jsdlb==0) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
					return 1;
				}
				else {
					return 0;
				}
			}
			else {
				return 0;
			}
			
		}
	}
	
		
	public boolean testCaseTetB(int i, int j,Case before,Case caseuh) {
			if ((caseuh.contientRail()||caseuh.contientMaritime())&&!caseuh.contientRoute()) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
					return true;
				}
				else {
					return false;
				}
			}
			else if(caseuh.contientRoute()&&(!caseuh.contientRail()&&!caseuh.contientMaritime())) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
					return true;
				}
				else {
					return false;
				}
			}
			
			else if(caseuh.contientRoute()&&(caseuh.contientRail()||caseuh.contientMaritime())) {
				if(caseuh.contientStationTrain()||caseuh.contientPort()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()||(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime())) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					if((before.contientRail()||before.contientMaritime())&&!before.contientRoute()) {
						if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
							return true;
						}
						else {
							return false;
						}
					}
					else if((!before.contientRail()&&!before.contientMaritime())&&before.contientRoute()) {
						if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
							return true;
						}
						else {
							return false;
						}
					}
					else {
						return false;
					}
				}
			}
			else {
				return false;
			}
	}
	
	public int testCaseTetBetBus(int i, int j,Case before,Case caseuh,int jsdlb) {
		if(caseuh.contientStationBus()) {
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
				return 2;
			}
			else if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&!carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
				return 1;
			}
			else if(!carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()){
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			if(jsdlb==1) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
					return 1;
				}
				else {
					return 0;
				}
			}
			else {
				if ((caseuh.contientRail()||caseuh.contientMaritime())&&!caseuh.contientRoute()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
						return 1;
					}
					else {
						return 0;
					}
				}
				else if(caseuh.contientRoute()&&(!caseuh.contientRail()&&!caseuh.contientMaritime())) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
						return 1;
					}
					else {
						return 0;
					}
				}
				else if(caseuh.contientRoute()&&(caseuh.contientRail()||caseuh.contientMaritime())) {
					if(caseuh.contientStationTrain()||caseuh.contientPort()) {
						if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()||(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime())) {
							return 1;
						}
						else {
							return 0;
						}
					}
					else {
						if((before.contientRail()||before.contientMaritime())&&!before.contientRoute()) {
							if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
								return 1;
							}
							else {
								return 0;
							}
						}
						else if((!before.contientRail()&&!before.contientMaritime())&&before.contientRoute()) {
							if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
								return 1;
							}
							else {
								return 0;
							}
						}
						else {
							return 0;
						}
					}
				}
				else {
					return 0;
				}
			}
		}
	}
	
	public int testCaseTetBus(int i, int j,Case before,Case caseuh,int jsdlb) {
		if(caseuh.contientStationBus()) {
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
				return 2;
			}
			else if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&!carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
				return 1;
			}
			else if(!carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()){
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			if(jsdlb==1) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
					return 1;
					}
				else {
					return 0;
				}
			}
			else {
				if (caseuh.contientRail()&&!caseuh.contientRoute()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()) {
						return 1;
					}
					else {
						return 0;
					}
				}
				else if(caseuh.contientRoute()&&!caseuh.contientRail()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
						return 1;
					}
					else {
						return 0;
					}
				}
				else if(caseuh.contientRoute()&&caseuh.contientRail()) {
					if(caseuh.contientStationTrain()) {
						if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()) {
							return 1;
						}
						else {
							return 0;
						}
					}
					else {
						if(before.contientRail()&&!before.contientRoute()) {
							if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRail()) {
								return 1;
							}
							else {
								return 0;
							}
						}
						else if(!before.contientRail()&&before.contientRoute()) {
							if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
								return 1;
							}
							else {
								return 0;
							}
						}
						else {
							return 0;
						}
					}
				}
				else {
					return 0;
				}
			}
		}
	}
	
	public int testCaseBetBus(int i, int j,Case before,Case caseuh,int jsdlb) {
		
		if(caseuh.contientStationBus()) {
			if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
				return 2;
			}
			else if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&!carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
				return 1;
			}
			else if(!carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()&&carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()){
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			if(jsdlb==1) {
				if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientBus()) {
					return 1;
				}
				else {
					return 0;
				}
			}
			else {
				if (caseuh.contientMaritime()&&!caseuh.contientRoute()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
						return 1;
					}
					else {
						return 0;
					}
				}
				else if(caseuh.contientRoute()&&!caseuh.contientMaritime()) {
					if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
						return 1;
					}
					else {
						return 0;
					}
				}
				else if(caseuh.contientRoute()&&caseuh.contientMaritime()) {
					if(caseuh.contientPort()) {
						if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()||carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
							return 1;
						}
						else {
							return 0;
						}
					}
					else {
						if(before.contientMaritime()&&!before.contientRoute()) {
							if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientMaritime()) {
								return 1;
							}
							else {
								return 0;
							}
						}
						else if(!before.contientMaritime()&&before.contientRoute()) {
							if(carte.getCase(caseuh.getColonne()+i,caseuh.getLigne()+j).contientRoute()) {
								return 1;
							}
							else {
								return 0;
							}
						}
						else {
							return 0;
						}
					}
				}
				else {
					return 0;
				}
			}
		}
	}
	
	
}
