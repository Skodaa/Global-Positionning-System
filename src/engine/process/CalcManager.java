 package engine.process;

import java.util.ArrayList;

import engine.exception.WrongParametersException;
import engine.map.Case;
import engine.map.Map;

public class CalcManager {
	
	private Map map;
	
	private int distance;
	private CalculeDistance calc;
	private CalcRapide calcTemp;
	private float temp;
	
	private String x1;
	private String y1;
	private String x2;
	private String y2;
	
	private Case start = new Case();
	private Case finish = new Case();
	
	public CalcManager(Map map) {
		this.map = map;
		start = null;
		finish = null;
	}
	
	public void setStartFinishPoint(Case position) throws WrongParametersException {
		if (start == null) {
			if(!map.isOnBorder(position)) {
				start = new Case(position.getLigne(),position.getColonne());
				System.out.println("Point de départ : " + position.getLigne() + " , " + position.getColonne());
			}
			else {
				throw new WrongParametersException("Erreur position point de départ");
			}
		}
		else if (start != null && finish == null) {
			if(!map.isOnBorder(position)) {
				finish = new Case(position.getLigne(),position.getColonne());
				System.out.println("Point d'arrivée : " + position.getLigne() + " , " + position.getColonne());
			}
			else {
				throw new WrongParametersException("Erreur position point d'arrivée");
			}
		}else {
			throw new WrongParametersException("Point de départ et d'arrivée déja enregistré");
		}
	}
	
	public void resetStartEnd() {
		start = null;
		finish = null;
		System.out.println("zizi");
	}
	
	public void calcLowerTraject(boolean train) throws WrongParametersException{
		if(start != null && finish != null) {
			calc = new CalculeDistance(map,map.getCase(start.getColonne(), start.getLigne()),map.getCase(finish.getColonne(), finish.getLigne()),train);
			distance = calc.testDist(calc.getDepart(),null);
			// temp = calcTemp.getTemp(); a voir 
		} else {
			throw new WrongParametersException("Les paramètres de départ et/ou d'arriver son érronés");
		}
	}
	
	public void calcTempTraject(boolean train) throws WrongParametersException{
		if(start != null && finish!= null) {
			calcTemp = new CalcRapide(map,map.getCase(start.getColonne(), start.getLigne()),map.getCase(finish.getColonne(), finish.getLigne()),train);
			distance = calcTemp.testDist(calcTemp.getDepart(),null);
			temp = calcTemp.getTemp();
		}else {
			throw new WrongParametersException("Les paramètres de départ et/ou d'arriver son érronés");
		}
	}
	
	public int getDistance() {
		return distance;
	}
	
	public float getTemp() {
		return temp;
	}
	
	public ArrayList<Case> getChemin(){
		if(calc!=null) {
			return calc.getCheminFinal();
		}
		else if(calcTemp!=null){
			return calcTemp.getCheminFinal();
		}
		else {
			return null;
		}
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

	public String getX1() {
		return x1;
	}
	
	public void setX1(String x1) {
		this.x1 = x1;
	}
	
	public String getY1() {
		return y1;
	}
	
	public void setY1(String y1) {
		this.y1 = y1;
	}
	
	public String getX2() {
		return x2;
	}
	
	public void setX2(String x2) {
		this.x2 = x2;
	}
	
	public String getY2() {
		return y2;
	}
	
	public void setY2(String y2) {
		this.y2 = y2;
	}
}
