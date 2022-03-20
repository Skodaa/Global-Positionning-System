 package engine.process;

import java.util.ArrayList;

import engine.config.GPSConfiguration;
import engine.item.Lieu;
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
	
	
	public CalcManager(Map map) {
		this.map = map;
	}
	
	public void calcLowerTraject(boolean train) {
		calc = new CalculeDistance(map,map.getCase(Integer.parseInt(x1), Integer.parseInt(y1)),map.getCase(Integer.parseInt(x2), Integer.parseInt(y2)),train);
		distance = calc.testDist(calc.getDepart(),null);
	}
	
	public void calcTempTraject(boolean train) {
		calcTemp = new CalcRapide(map,map.getCase(Integer.parseInt(x1), Integer.parseInt(y1)),map.getCase(Integer.parseInt(x2), Integer.parseInt(y2)),train);
		distance = calcTemp.testDist(calcTemp.getDepart(),null);
		temp = calcTemp.getTemp();
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
