package engine.process;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.config.GPSConfiguration;
import engine.imageIn.Image;
import engine.map.Map;

public class GPSBuilder {
	public static Map buildMap() throws IOException {
		File file = new File(GPSConfiguration.MAP_EMPLACEMENT);
		BufferedImage imageI = ImageIO.read(file);
		Image image = new Image(imageI);
		return new Map(image);
	}
	
	public static CalcManager buildInit(Map map) {
		CalcManager manager = new CalcManager(map);
		
		return manager;
	}
}
