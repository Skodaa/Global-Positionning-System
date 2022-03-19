package engine.process;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import engine.item.ElementCarte;
import engine.item.StationTrain;
import engine.map.Case;


public class CSVReader {
	
	private String line = "";  
	private String spliter = ";";
	private ArrayList<String[]> pointlist = new ArrayList<String[]>();
	private String file;
	
	public CSVReader(String file) {
		this.file = file;
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

}
