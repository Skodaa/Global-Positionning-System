package engine.map;



import java.util.ArrayList;

import engine.imageIn.Image;
import engine.item.Road;
import engine.item.Water;


public class Map {
	private Case[][] map;
	private int hauteur;
	private int largeur;
	
	public Map(Image image) {
		this.hauteur=image.getHauteur();
		this.largeur=image.getLargeur();
		this.map = new Case[image.getLargeur()][image.getHauteur()];
		for(int i = 0;i < image.getLargeur();i++) {
			for(int j = 0;j < image.getHauteur();j++) {
				
				map[i][j]=new Case(i,j);
				attributClasse(map[i][j],image,i,j);
				
			}
		}
	}
	
	public void attributClasse(Case currentCase,Image image,int i,int j) {
		if(image.getPixel(i,j).getBleu()==255) {
			currentCase.appendNewIP(new Water());
		}
		else if((image.getPixel(i, j).getRouge()==255)&&image.getPixel(i, j).getVert()==255){
			Road road = new Road("city");
			currentCase.appendNewIP(road);
		}
		
	}
	
	public int testDist(Case depart,Case arrive,int count,int rep) {
		int xi = depart.getColonne();
		int yi = depart.getLigne();
		
		int xa = arrive.getColonne();
		int ya = arrive.getLigne();
		
		int sousx = xa -xi;
		sousx = Math.abs(sousx);
		int sousy = ya -yi;
		sousy = Math.abs(sousy);
		
		
		ArrayList<Case> adja = caseAdjacente(depart);
		for(int i = 0;i<adja.size();i++) {
			if(adja.get(i)==arrive) {
				return count + 1;
			}
			if((Math.abs((xa - adja.get(i).getColonne()))<sousx)||(Math.abs((ya - adja.get(i).getLigne()))<sousy)){
				count = testDist(adja.get(i),arrive,count+1,rep);
				if((rep == 0)||(count < rep)) {
					rep = count;
				}
			}
		}
		return rep;
	}
	
	public int estIntersection(Case block) {
		if(block.haveRoad()==1) {
			ArrayList<Case> adja = caseAdjacente(block);
			if(adja.size()>2) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			return -1;
		}
	}
	
	public Case[][] getMap() {
		return map;
	}
	
	public ArrayList<Case> caseAdjacente(Case caseuh){
		ArrayList<Case> route = new ArrayList<Case>(); 
		
		int i = 0;
		int j = -1;
		if ((map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1)) {
			route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
		}
		
		i = -1;
		j = 0;
		if ((map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1)) {
			route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
		}
		
		i = 1;
		j = 0;
		if ((map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1)) {
			route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
		}
		
		i = 0;
		j = 1;
		
		if ((map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1)) {
			route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
		}
	
		
		return route;
	}
	
	public Case getCase(int x,int y) {
		return map[x][y];
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
}
