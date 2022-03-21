package engine.map;



import java.util.ArrayList;

import engine.config.GPSConfiguration;
import engine.imageIn.Image;
import engine.item.Rail;
import engine.item.Road;
import engine.item.StationTrain;
import engine.item.Water;
import engine.process.CSVReader;


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
		CSVReader cr = new CSVReader(GPSConfiguration.GARES_CSV);
		ArrayList<String[]> gares = cr.recupPoint();
		for(String[] gare : gares) {
			StationTrain st = new StationTrain(gare[0],Integer.parseInt(gare[1]),Integer.parseInt(gare[2]));
			map[Integer.parseInt(gare[1])][Integer.parseInt(gare[2])].appendNewIP(st);
		}
	}
	
	public void attributClasse(Case currentCase,Image image,int i,int j) {
		if(image.getPixel(i,j).getBleu()==255) {
			currentCase.appendNewIP(new Water());
		}
		else if((image.getPixel(i, j).getRouge()==255)&&image.getPixel(i, j).getVert()==255){
			Road road = new Road("ville");
			currentCase.appendNewIP(road);
		}
		else if((image.getPixel(i, j).getRouge()==255)&&image.getPixel(i, j).getVert()==127){
			Road road = new Road("campagne");
			currentCase.appendNewIP(road);
		}
		
		else if(((image.getPixel(i, j).getRouge()==255)&&(image.getPixel(i, j).getVert()==0))&&(image.getPixel(i, j).getBleu()==0)){
			Road road = new Road("campagne");
			currentCase.appendNewIP(road);
			currentCase.appendNewIP(new Rail());
		}
		else if(((image.getPixel(i, j).getRouge()==0)&&(image.getPixel(i, j).getVert()==255))&&(image.getPixel(i, j).getBleu()==0)){
			currentCase.appendNewIP(new Rail());
		}
		
	}
	
	
	public int estIntersection(Case before,Case block,boolean train) {
		if(block.haveChemin()==1) {
			if(block.haveRail()==1&&block.haveStationTrain()==0) {
				return 0;
				}
				else {
				ArrayList<Case> adja = caseAdjacente(before,block,train);
				if(adja.size()>2) {
					return 1;
				}
				else {
					return 0;
				}
			}
			
		}
		else {
			return -1;
		}
	}
	
	public Case[][] getMap() {
		return map;
	}
	
	public ArrayList<Case> caseAdjacente(Case before,Case caseuh, boolean train){
		ArrayList<Case> route = new ArrayList<Case>(); 
		if(train) {
			int i = 0;
			int j = -1;
			
			if(testCaseTrain(i,j,before,caseuh)) {
				route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
			}
			
			i = -1;
			j = 0;
			if(testCaseTrain(i,j,before,caseuh)) {
				route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
			}
			
			
			i = 1;
			j = 0;
			if(testCaseTrain(i,j,before,caseuh)) {
				route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
			}
			
			i = 0;
			j = 1;
			if(testCaseTrain(i,j,before,caseuh)) {
				route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
			}
		}
		else {
			 int i = 0;
			 int j = -1;
			if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1) {
				route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
			}
			 i = -1;
			 j = 0;
			if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1) {
				route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
			}
			 i = 1;
			 j = 0;
			if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1) {
				route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
			}
			 i = 0;
			 j = 1;
			if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1) {
				route.add(map[caseuh.getColonne()+i][caseuh.getLigne()+j]);
			}
		}
		
		return route;
	}
	
	public boolean testCaseTrain(int i, int j,Case before,Case caseuh) {
		if (caseuh.haveRail()==1&&caseuh.haveRoad()==0) {
			if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRail()==1) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(caseuh.haveRoad()==1&&caseuh.haveRail()==0) {
			if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(caseuh.haveRoad()==1&&caseuh.haveRail()==1) {
			if(caseuh.haveStationTrain()==1) {
				if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1||map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRail()==1) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(before.haveRail()==1&&before.haveRoad()==0) {
					if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRail()==1) {
						return true;
					}
					else {
						return false;
					}
				}
				else if(before.haveRail()==0&&before.haveRoad()==1) {
					if(map[caseuh.getColonne()+i][caseuh.getLigne()+j].haveRoad()==1) {
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
	
	
	public Case getCase(int x,int y) {
		return map[x][y];
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public boolean isOnTopBorder(Case position) {
		int line = position.getLigne();
		return line == 0;
	}
	
	public boolean isOnBottomBorder(Case position) {
		int line = position.getLigne();
		return line == hauteur - 1;
	}
	
	public boolean isOnRightBorder(Case position) {
		int column = position.getColonne();
		return column == 0;
	}
	
	public boolean isOnLeftBorder(Case position) {
		int column = position.getColonne();
		return column == largeur - 1;
	}
	
	public boolean isOnBorder(Case position) {
		return isOnTopBorder(position) || isOnBottomBorder(position) || isOnRightBorder(position) || isOnLeftBorder(position);
	}
}
