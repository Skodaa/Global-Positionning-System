package engine.imageIn;

import java.awt.Color;

public class Pixel {
	private int rouge;
	private int vert;
	private int bleu;
	private int alpha;
	
	
	public Pixel(Color couleur) {
		this.rouge = couleur.getRed();
		
		this.vert = couleur.getGreen();
		
		this.bleu = couleur.getBlue();
		
		this.alpha = couleur.getAlpha();
		
	}
	
	public int getBleu() {
		return bleu;
	}
	
	public int getRouge() {
		return rouge;
	}
	
	public int getVert() {
		return vert;
	}
}
