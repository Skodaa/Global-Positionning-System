package engine.process;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.config.GPSConfiguration;
import engine.image.Image;
import engine.map.Carte;


public class GPSBuilder {
	public static Carte buildMap() throws IOException {
		File file = new File(GPSConfiguration.MAP_EMPLACEMENT);
		BufferedImage imageI = ImageIO.read(file);
		Image image = new Image(imageI);
		return new Carte(image);
	}
	
	public static CalculeTrajetManager buildInit(Carte carte) {
		CalculeTrajetManager manager = new CalculeTrajetManager(carte);
		
		return manager;
	}
}
