package engine.map;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import engine.config.GPSConfiguration;
import engine.image.Image;
import engine.item.*;
import engine.process.CSVReader;
import log.LoggerUtility;

public class Carte {
	
	//Création du logger
	private static Logger logger = LoggerUtility.getLogger(Carte.class, "html");
	
	//Atribut de la Map
	private Case[][] carte;
	private int hauteur;
	private int largeur;
	
	
	//Constructeur de la Carte
	public Carte(Image image) {
		this.hauteur = image.getHauteur();
		this.largeur = image.getLargeur();
		this.carte = new Case[image.getLargeur()][image.getHauteur()];
		
		for(int i = 0; i < image.getLargeur(); i++) {
			for(int j = 0; j < image.getHauteur(); j++) {
				
				carte[i][j]=new Case(i,j);
				attributElement(carte[i][j], image, i, j);
			}
		}
		
		//Lecture des CSV
		CSVReader tread = new CSVReader(GPSConfiguration.GARES_CSV);
		CSVReader bread = new CSVReader(GPSConfiguration.PORT_CSV);
		CSVReader cread = new CSVReader(GPSConfiguration.BUS_CSV);
		CSVReader lread = new CSVReader(GPSConfiguration.LIEU_CSV);
		ArrayList<String[]> gares = tread.recupPoint();
		for(String[] gare : gares) {
			StationTrain st = new StationTrain(gare[0],Integer.parseInt(gare[1]),Integer.parseInt(gare[2]));
			carte[Integer.parseInt(gare[1])][Integer.parseInt(gare[2])].ajouterElement(st);
		}
		ArrayList<String[]> ports = bread.recupPoint();
		for(String[] port : ports) {
			Port pt = new Port(port[0],Integer.parseInt(port[1]),Integer.parseInt(port[2]));
			carte[Integer.parseInt(port[1])][Integer.parseInt(port[2])].ajouterElement(pt);
		}
		ArrayList<String[]> arrets = cread.recupPoint();
		for(String[] arret : arrets) {
			StationBus pt = new StationBus(arret[0],Integer.parseInt(arret[1]),Integer.parseInt(arret[2]));
			carte[Integer.parseInt(arret[1])][Integer.parseInt(arret[2])].ajouterElement(pt);
		}
		ArrayList<String[]> lieux = lread.recupPoint();
		for(String[] lieu : lieux) {
			Lieu lt = new Lieu(lieu[0],lieu[1],lieu[2]);
			carte[Integer.parseInt(lieu[1])][Integer.parseInt(lieu[2])].ajouterElement(lt);
		}
	}
	
	//methode d'attribution d'ElementCarte
	public void attributElement(Case caseActuel, Image image, int i, int j) {
		int rouge = image.getPixel(i, j).getRouge();
		int vert = image.getPixel(i, j).getVert();
		int bleu = image.getPixel(i, j).getBleu();
		
		if(((rouge==255)&&(vert==0))&&(bleu==0)){
			caseActuel.ajouterElement(new Route());
			caseActuel.ajouterElement(new Rail());
			logger.info("Création Case de route + rail en" +i +" " +j);
		}
		
		else if(((rouge==34)&&(vert==177))&&(bleu==76)) {
			caseActuel.ajouterElement(new Foret());
			logger.info("Création Case de forêt en" +i +" " +j);
		}
				
		else if(((rouge==180)&&(vert==0))&&(bleu==190)){
			caseActuel.ajouterElement(new Route());
			caseActuel.ajouterElement(new Maritime());
			logger.info("Création Case de route + Maritime en" +i +" " +j);
		}
		
		else if((rouge==255)&&vert==255&&(bleu==0)){
			caseActuel.ajouterElement(new Route());
			logger.info("Création Case de route" +i +" " +j);
		}
		else if(((rouge==0)&&(vert==0))&&(bleu==255)) {
			caseActuel.ajouterElement(new Eau());
			logger.info("Création Case d'eau en" +i +" " +j);
		}
		else if(((rouge==0)&&(vert==255))&&(bleu==0)) {
			caseActuel.ajouterElement(new Rail());
			logger.info("Création Case de Rail en" +i +" " +j);
		}
		else if(((rouge==0)&&(vert==120))&&(bleu==255)) {
			caseActuel.ajouterElement(new Maritime());
			logger.info("Création Case Maritime en" +i +" " +j);
		}
		else if(((rouge==200)&&(vert==191))&&(bleu==231)) {
			caseActuel.ajouterElement(new Bus("A"));
			caseActuel.ajouterElement(new Route());
			logger.info("Création Case Bus A en" +i +" " +j);
		}
		else if(((rouge==153)&&(vert==217))&&(bleu==234)) {
			caseActuel.ajouterElement(new Bus("B"));
			caseActuel.ajouterElement(new Route());
			logger.info("Création Case Bus B en" +i +" " +j);
		}
		else if(((rouge==239)&&(vert==228))&&(bleu==176)) {
			caseActuel.ajouterElement(new Bus("A"));
			caseActuel.ajouterElement(new Route());
			caseActuel.ajouterElement(new Rail());
			logger.info("Création Case Bus A en" +i +" " +j);
		}
		else if(((rouge==163)&&(vert==73))&&(bleu==164)) {
			caseActuel.ajouterElement(new Bus("B"));
			caseActuel.ajouterElement(new Route());
			caseActuel.ajouterElement(new Rail());
			logger.info("Création Case Bus B en" +i +" " +j);
		}
	}
	
	//Getter Setters
	public Case[][] getCarte(){
		return carte;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public Case getCase(int i,int j) {
		return carte[i][j];
	}
	
	
	
}
