package ihm.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import engine.map.Case;
import engine.map.Map;
import engine.process.CalcManager;

public class DisplayMap extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton itineraireButton = new JButton("Itineraire");
	
	private Map map;
	private CalcManager manager;
	private ArrayList<Case> chemin;
	private PaintStrategy paintStrategy = new PaintStrategy();
	
	
	
	public DisplayMap(Map map, CalcManager manager) {
		this.map = map;
		this.manager = manager;
		this.chemin = manager.getChemin();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		paintStrategy.paint(map, g);
		
		if(chemin != null) {
			paintStrategy.paint(map, g, chemin );
		}
		
	}
}
