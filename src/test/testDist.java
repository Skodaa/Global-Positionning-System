package test;

import ihm.gui.MainGUI;

public class testDist {
	public void main(String args[]) {
		MainGUI gameMainGUI = new MainGUI("GPS");
		int xi = 21;
		int yi = 26;
		
		int xa = 43;
		int ya = 11;
		
		
		Thread gameThread = new Thread(gameMainGUI);
		gameThread.start();
	}
}
