package test;

import ihm.gui.MainGUI;

public class testGUI {
	
	public static void main(String[] args) {

		MainGUI gameMainGUI = new MainGUI("GPS");

		Thread gameThread = new Thread(gameMainGUI);
		gameThread.start();
	}

}
