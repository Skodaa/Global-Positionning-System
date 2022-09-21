package ihm.gui;

import java.awt.Color; 
import java.awt.Graphics;
import java.util.ArrayList;

import engine.config.GPSConfiguration;
import engine.item.Lieu;
import engine.map.Carte;
import engine.map.Case;
import engine.process.CSVReader;
import engine.process.ImageReader;


public class PaintStrategy {

	public void paint(Carte carte, Graphics graphics) {
		int blockSize = GPSConfiguration.BLOCK_SIZE;
		Case[][] blocks = carte.getCarte();

		for (int columnIndex = 0; columnIndex < carte.getLargeur(); columnIndex++) {
			for (int lineIndex = 0; lineIndex < carte.getHauteur(); lineIndex++) {
				Case block = blocks[columnIndex][lineIndex];
				if (block.contientStationTrain()) {
					graphics.setColor(Color.BLACK);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				else if (block.contientPort()) {
					graphics.setColor(Color.BLACK);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				else if (block.contientRail()&&block.contientRoute()) {
					graphics.setColor(Color.GREEN);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				else if (block.contientMaritime()&&block.contientRoute()) {
					graphics.setColor(new Color(180,0,190));
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				

				else if (block.contientRoute()) {
					graphics.setColor(Color.YELLOW);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				
				else if (block.contientEau()) {
					graphics.setColor(Color.BLUE);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				else if (block.contientForet()) {
					graphics.setColor(new Color(34,177,76));
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				else if (block.contientRail()) {
					graphics.setColor(new Color(0,100,0));
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				else if (block.contientMaritime()) {
					graphics.setColor(Color.CYAN);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
				else {
					graphics.setColor(Color.GRAY);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
				}
			}
		}
	}
	
	public void paintStation(Carte map, Graphics graphics) {
		int blockSize = GPSConfiguration.BLOCK_SIZE;
		Case[][] blocks = map.getCarte();
		CSVReader csv = new CSVReader(GPSConfiguration.LIEU_CSV);
		ArrayList<Lieu> lieux = csv.getLieu();
		
		for (int columnIndex = 0; columnIndex < map.getLargeur(); columnIndex++) {
			for (int lineIndex = 0; lineIndex < map.getHauteur(); lineIndex++) {
				Case block = blocks[columnIndex][lineIndex];
				for(Lieu lieu : lieux) {
					if((block.getColonne() == Integer.parseInt(lieu.getX())) && (block.getLigne() == Integer.parseInt(lieu.getY()))) {
						graphics.setColor(Color.BLACK);
						graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
						graphics.drawImage(ImageReader.readImage("src/ihm/images/lieu.png"),(block.getColonne()*blockSize)-13,(block.getLigne()*blockSize)-12, 30, 30, null);
					}
				}
				
				if (block.contientStationTrain()) {
					graphics.setColor(Color.BLACK);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
					graphics.drawImage(ImageReader.readImage("src/ihm/images/station.png"),(block.getColonne()*blockSize)-13,(block.getLigne()*blockSize)-27, 30, 30, null);
				}
				else if (block.contientPort()) {
					graphics.setColor(Color.BLACK);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
					graphics.drawImage(ImageReader.readImage("src/ihm/images/port.png"),(block.getColonne()*blockSize)-13,(block.getLigne()*blockSize)-27, 30, 30, null);
				}
				else if (block.contientStationBus()) {
					graphics.setColor(Color.BLACK);
					graphics.fillRect(block.getColonne() * blockSize, block.getLigne() * blockSize, blockSize, blockSize);
					if((block.getColonne() * blockSize < 700)&&(block.getLigne() * blockSize > 500)){
						graphics.drawImage(ImageReader.readImage("src/ihm/images/arretB.png"),(block.getColonne()*blockSize)-17,(block.getLigne()*blockSize)-27, 30, 30, null);
					}
					else {
						graphics.drawImage(ImageReader.readImage("src/ihm/images/arretA.png"),(block.getColonne()*blockSize)-17,(block.getLigne()*blockSize)-27, 30, 30, null);
					}
				}
			}
		}
	}

	
	public void paint(Carte carte,Graphics graphics,ArrayList<Case> chemin,ArrayList<Integer> bus) {
		int blockSize = GPSConfiguration.BLOCK_SIZE;
		Case start = chemin.get(0);
		Case end = chemin.get(chemin.size()-1);
		
		for(int i = 0;i < chemin.size();i++) {
			if(chemin.get(i).contientMaritime()) {
				graphics.setColor(new Color(58, 216, 125));
				graphics.fillRect(chemin.get(i).getColonne() * blockSize, chemin.get(i).getLigne() * blockSize, blockSize, blockSize);
			}
			else if((chemin.get(i).contientRail()&&i!=0)&&i!=chemin.size()-1) {
				 if(chemin.get(i-1).contientRail()||chemin.get(i+1).contientRail()) {
					graphics.setColor(new Color(199, 15, 219));
					graphics.fillRect(chemin.get(i).getColonne() * blockSize, chemin.get(i).getLigne() * blockSize, blockSize, blockSize);
				 }
				 else {
					graphics.setColor(Color.RED);
					graphics.fillRect(chemin.get(i).getColonne() * blockSize, chemin.get(i).getLigne() * blockSize, blockSize, blockSize);
				}
			}
			else if(bus.get(i)==1) {
				graphics.setColor(new Color(250,149,25));
				graphics.fillRect(chemin.get(i).getColonne() * blockSize, chemin.get(i).getLigne() * blockSize, blockSize, blockSize);
			}
			else {
				graphics.setColor(Color.RED);
				graphics.fillRect(chemin.get(i).getColonne() * blockSize, chemin.get(i).getLigne() * blockSize, blockSize, blockSize);
			}
		}
		graphics.drawImage(ImageReader.readImage("src/ihm/images/depart.png"),(start.getColonne()*5)-13,(start.getLigne()*5)-27, 30, 30, null);
		graphics.drawImage(ImageReader.readImage("src/ihm/images/arrive.png"),(end.getColonne()*5)-13,(end.getLigne()*5)-27, 30, 30, null);
	}
}
