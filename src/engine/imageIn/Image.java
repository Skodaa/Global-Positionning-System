package engine.imageIn;

import java.awt.Color;
import java.awt.image.BufferedImage;

import engine.imageIn.Pixel;

public class Image {
	private int hauteur;
	private int largeur;
	private BufferedImage image;
	private Pixel[][] tabPixel;
	
	public Image(BufferedImage image) {
		this.hauteur = image.getHeight();
		this.largeur = image.getWidth();
		this.image = image;
		this.tabPixel = new Pixel[largeur][hauteur];
		for(int colonne = 0; colonne < largeur;colonne++) {
			for(int ligne = 0; ligne < hauteur; ligne++) {
				tabPixel[colonne][ligne] = new Pixel(new Color(image.getRGB(colonne, ligne),true));
			}
		}
	}
	
	public Pixel getPixel(int x,int y) {
		return tabPixel[x][y];
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public int getLargeur() {
		return largeur;
	}
}
