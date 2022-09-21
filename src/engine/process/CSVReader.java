package engine.process;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import engine.config.GPSConfiguration;
import engine.item.Lieu;


public class CSVReader {
	
	private String line = "";  
	private String spliter = ";";
	private ArrayList<String[]> pointlist = new ArrayList<String[]>();
	private String file;
	
	public CSVReader(String file) {
		this.file = file;
	}
	public CSVReader() {

	}
	
	public ArrayList<String[]> recupPoint(){
		
		try{   
			BufferedReader br = new BufferedReader(new FileReader(file));  
			while ((line = br.readLine()) != null){  
				String[] lieu = line.split(spliter);     
				
				pointlist.add(lieu);
				
			}  
			
			br.close();
			
		}   
		catch (IOException e){  
			e.printStackTrace();  
		} 
		
		
		return pointlist;
	}

	public ArrayList<Lieu> getLieu() {
		ArrayList<String[]> lieux = recupPoint();
		ArrayList<Lieu> out = new ArrayList<Lieu>();
		for(String[] lieu : lieux) {
			Lieu st = new Lieu(lieu[0],lieu[1],lieu[2]);
			out.add(st);
		}
		return out;
		
	}
	
	public ArrayList<Lieu> getAllLieu() {
		CSVReader gareread = new CSVReader(GPSConfiguration.GARES_CSV);
		CSVReader portread = new CSVReader(GPSConfiguration.PORT_CSV);
		CSVReader busread = new CSVReader(GPSConfiguration.BUS_CSV);
		CSVReader lieuread = new CSVReader(GPSConfiguration.LIEU_CSV);
		ArrayList<Lieu> allOut = new ArrayList<Lieu>();
		ArrayList<String[]> lieux = new ArrayList<String[]>();
		lieux.addAll(gareread.recupPoint());
		lieux.addAll(portread.recupPoint());
		lieux.addAll(busread.recupPoint());
		lieux.addAll(lieuread.recupPoint());
		for(String[] lieu : lieux) {
			Lieu st = new Lieu(lieu[0],lieu[1],lieu[2]);
			allOut.add(st);
		}
		return allOut;	
		
	}
}
