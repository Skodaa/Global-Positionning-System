 package engine.process;

import java.util.ArrayList;

import engine.map.Case;
import engine.map.Map;

public class CalcManager {
	
	private Map map;
	
	private int distance;
	private CalculeDistance calc;
	
	private String x1;
	private String y1;
	private String x2;
	private String y2;
	
	
	public CalcManager(Map map) {
		this.map = map;
	}
	
	public void calcLowerTraject() {
		calc = new CalculeDistance(map,map.getCase(Integer.parseInt(x1), Integer.parseInt(y1)),map.getCase(Integer.parseInt(x2), Integer.parseInt(y2)));
		distance = calc.testDist(calc.getDepart(),null);
	}
	
	public int getDistance() {
		return distance;
	}
	
	public ArrayList<Case> getChemin(){
		if(calc!=null) {
			return calc.getCheminFinal();
		}
		else {
			return null;
		}
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
