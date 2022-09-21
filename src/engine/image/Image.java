package engine.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Image {
	
	//attribut de class
	private int hauteur;
	private int largeur;
	private Pixel[][] pixels;
	
	
	//constructeur de la class
	public Image(BufferedImage image) {
		this.hauteur = image.getHeight();
		this.largeur = image.getWidth();
		this.pixels = new Pixel[largeur][hauteur];
		
		for(int colonne = 0; colonne < largeur; colonne++) {
			for(int ligne = 0; ligne < hauteur; ligne++) {
				pixels[colonne][ligne] = new Pixel(new Color(image.getRGB(colonne, ligne),true));
			}
		}
	}
	
	//Getters et Setters
	public int getHauteur() {
		return hauteur;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public Pixel[][] getPixels(){
		return pixels;
	}
	
	public Pixel getPixel(int i, int j) {
		return pixels[i][j];
	}
}
