package ihm.gui;
 
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.map.Carte;
import engine.map.Case;
import engine.process.CalculeTrajetManager;

public class DisplayMap extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Carte carte;
	private ArrayList<Case> chemin;
	private ArrayList<Integer> bus;

	private PaintStrategy paintStrategy = new PaintStrategy();
	
	
	
	public DisplayMap(Carte carte, CalculeTrajetManager manager) {
		this.carte = carte;
		this.chemin = manager.getChemin();
		this.bus = manager.getBus();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		paintStrategy.paint(carte, g);
		paintStrategy.paintStation(carte,g);
		if(chemin!=null) {
			if(chemin.size() != 0) {
				paintStrategy.paint(carte, g, chemin, bus );
				paintStrategy.paintStation(carte,g);
			}	
		}
	}
}
