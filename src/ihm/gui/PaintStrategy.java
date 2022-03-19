package ihm.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import engine.config.GPSConfiguration;
import engine.map.Case;
import engine.map.Map;



public class PaintStrategy {

	public void paint(Map map, Graphics graphics) {
		int blockSize = GPSConfiguration.BLOCK_SIZE;
		Case[][] blocks = map.getMap();

		for (int columnIndex = 0; columnIndex < map.getLargeur(); columnIndex++) {
			for (int lineIndex = 0; lineIndex < map.getHauteur(); lineIndex++) {
				Case block = blocks[columnIndex][lineIndex];
				
				if (block.haveRail()==1&&block.haveRoad()==1) {
					graphics.setColor(Color.GREEN);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}

				else if (block.haveRoad()==1) {
					graphics.setColor(Color.YELLOW);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				
				else if (block.haveWater()==1) {
					graphics.setColor(Color.BLUE);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				else if (block.haveRail()==1) {
					graphics.setColor(new Color(0,100,0));
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				
				else if (block.getNum()==0) {
					graphics.setColor(Color.GRAY);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				
			}
		}
	}
	
	public void paint(Map map,Graphics graphics,ArrayList<Case> chemin) {
		int blockSize = GPSConfiguration.BLOCK_SIZE;
		
		for(int i = 0;i < chemin.size();i++) {
			graphics.setColor(Color.RED);
			graphics.fillRect(chemin.get(i).getColonne() * blockSize, chemin.get(i).getLigne() * blockSize, blockSize, blockSize);
		}
	}
}
