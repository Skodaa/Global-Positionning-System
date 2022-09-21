package engine.image;

import java.awt.Color;

public class Pixel {
	
	//attribut de la classe
	private int rouge;
	private int vert;
	private int bleu;
	
	//Constructeur d'un Pixel
	public Pixel(Color couleur) {
		this.rouge = couleur.getRed();
		this.vert = couleur.getGreen();
		this.bleu = couleur.getBlue();
	}
	
	//Getters et Setters
	public int getRouge() {
		return rouge;
	}
	
	public int getVert() {
		return vert;
	}
	
	public int getBleu() {
		return bleu;
	}
}
