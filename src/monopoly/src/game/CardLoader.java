package monopoly.src.game;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.imageio.ImageIO;

import src.main.java.org.apache.commons.csv.CSVFormat;
import src.main.java.org.apache.commons.csv.CSVFormat.CSVFormatBuilder;
import src.main.java.org.apache.commons.csv.CSVRecord;

public class CardLoader {
	public Image dice1 = null;
	public Image dice2 = null;
	public Image board = null;
	public Image[] chanceCards = null;
	public Image[] companyCards = null;
	public Image[] terrainCards = null;
	public Image[] pins = null;

	
	
	/*Novas variáveis para o novo parser*/
	String paths[] = new String[4]; // 0 chance, 1 company, 2 terrain, 3 pin
	String names[] = new String[4];
	
	public void load(String dataFile,Chance []cardChance,Property []properties,Pin []pins) {
		Reader input;
		try {
			input = new FileReader (dataFile);
			Iterable<CSVRecord> lines = CSVFormat.EXCEL.parse(input);
			
			int i=0;
			for(CSVRecord line:lines){
				paths[i]= new String();
				names[i]= new String();
				paths[i]=line.get(0);//paths
				names[i]=line.get(1);//names
				i++;
				//System.out.println(paths[i]+names[i]);
			}
			/*Now opens each file to set it's objects*/
			
			System.out.println(paths[0]+names[0]);
			input= new FileReader (paths[0]+names[0]); //CHANCE
			lines = CSVFormat.EXCEL.parse(input);
			i=0;
			for(CSVRecord line:lines){
				cardChance[i] = new Chance(
						line.get(1).charAt(0),//"function"
						Integer.parseInt(line.get(2)),//"value"
						ImageIO.read(new File(paths[0]+line.get(0)))//"name"
						);
				i++;
			}
			
			input= new FileReader (paths[1]+names[1]);//Company
			lines = CSVFormat.EXCEL.parse(input);
			i=0;
			for(CSVRecord line:lines){
				System.out.println(paths[1]+line.get(0));
				properties[i] =(Property) new Company(
						line.get(0),//"name"
						Integer.parseInt(line.get(1)),//"price"
						Integer.parseInt(line.get(2)),//"multiplier"
						Integer.parseInt(line.get(3)),//"mortgage"
						Integer.parseInt(line.get(4)),//"location"
						ImageIO.read(new File(paths[1]+line.get(0)))//"name"
						);
				i++;

			}
			
			input= new FileReader (paths[2]+names[2]);//terrain
			lines = CSVFormat.EXCEL.parse(input);
			//i=0;
			for(CSVRecord line:lines){
				properties[i] = (Property) new Terrain(
						line.get(0),//"name"
						Integer.parseInt(line.get(1)),//"price"
						Integer.parseInt(line.get(2)),//"rent"
						Integer.parseInt(line.get(3)),//"mortgage"
						Integer.parseInt(line.get(4)),//"location"
						ImageIO.read(new File(paths[2]+line.get(0))),//"name"
						new Integer[] {
							Integer.parseInt(line.get(5)),//"house1"
							Integer.parseInt(line.get(6)),//"house2"
							Integer.parseInt(line.get(7)),//"house3"
							Integer.parseInt(line.get(8)),//"house4"
							Integer.parseInt(line.get(9))//"hotel"
							},
							Integer.parseInt(line.get(10))//"construction"
						);
				i++;
			}
			
			input= new FileReader (paths[3]+names[3]);//pin
			lines = CSVFormat.EXCEL.parse(input);
			i=0;
			for(CSVRecord line:lines){
				pins[i] = new Pin(
						line.get("color"),
						ImageIO.read(new File(paths[3]+line.get("name")))
						);
				i++;

			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
}
}