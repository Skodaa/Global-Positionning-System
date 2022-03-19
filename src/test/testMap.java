package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.imageIn.Image;
import engine.map.Map;
import engine.process.CalculeDistance;

public class testMap {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\maxmc\\Desktop\\oui.png");
		try {
			BufferedImage imageI = ImageIO.read(file);
			Image image = new Image(imageI);
			Map map = new Map(image);
			
			
			CalculeDistance calc = new CalculeDistance(map,map.getCase(21, 26),map.getCase(2, 127));
			int rep = calc.testDist(map.getCase(21, 26));
			
			System.out.println("La distance est : " +  rep);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
