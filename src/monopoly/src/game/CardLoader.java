package monopoly.src.game;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class CardLoader {
	public Image dice1 = null;
	public Image dice2 = null;
	public Image board = null;
	public Image[] chanceCards = null;
	public Image[] companyCards = null;
	public Image[] terrainCards = null;
	public Image[] pins = null;
	
	private static int chanceSize;
	private static int companySize;
	private static int terrainSize;
	private static int pinSize;
	
	private static String[] chanceImagePaths;
	private static String[] companyImagePaths;
	private static String[] terrainImagePaths;
	private static String[] pinImagePaths;
	
	private static Chance chance[];
	private static Company company[];
	private static Terrain terrain[];
	 class Chance{
		public String imagePath;
		public int value;
		public char special;
		public void setP(String a){
			imagePath=a;
		}
	}
	
	class Company {
		public String imagePath;
		public int multiplier;
		public int mortgage;
		
	}
	
	class Terrain{
		public String imagePath;
		public int rent;
		public int house[]= new int [4];
		public int hotel;
		public int houseCost;
		public int hotelCost;
		public int mortgage;
		
	}
	
	private static void readFile(File dataFile){
		
		FileInputStream f = null;
		BufferedReader reader = null;
		
		try{
			f = new FileInputStream(dataFile);
			reader = new BufferedReader(new InputStreamReader(f));
			
			String line = reader.readLine();
            while(line != null){
            	// Setting arrays sizes
                if(line.compareTo("# NUMBER OF CHANCE CARDS") == 0) {
                	line = reader.readLine();
                	chanceSize = Integer.parseInt(line);
          
                }
                
                if(line.compareTo("# NUMBER OF COMPANY CARDS") == 0) {
                	line = reader.readLine();
                	companySize = Integer.parseInt(line);
                	company = new Company[companySize];
                	/*System.out.println(companySize);*/
                }

                if(line.compareTo("# NUMBER OF TERRAIN CARDS") == 0) {
                	line = reader.readLine();
                	terrainSize = Integer.parseInt(line);
                	/*System.out.println(terrainSize);*/
                }
                
                if(line.compareTo("# NUMBER OF PINS") == 0) {
                	line = reader.readLine();
                	pinSize = Integer.parseInt(line);
                	/*System.out.println(pinSize);*/
                }
                
                // Populating arrays
                if(line.compareTo("# PATH OF EACH CHANCE CARD") == 0) {
                	line = reader.readLine();
                	String mainFolders = line;
                	chanceImagePaths = new String[chanceSize];
                	chance = new Chance[chanceSize];
                	line = reader.readLine();
                	String parts[];
                	for(int i = 0; i < chanceSize; i++){
                		chanceImagePaths[i] = mainFolders+line;
                		System.out.println(chanceImagePaths[i]);
                		chance[i].setP(mainFolders+line) ; //Nessa Linha
                		System.out.println(chance[i].imagePath);
                		line = reader.readLine();
                		parts = line.split(" ");
                		if (parts[0].compareTo("+")==0){
                			chance[i].value= Integer.parseInt(parts[1]);
                			chance[i].special ='A';
                		}
                		else if(parts[0].compareTo("-")==0){
                			chance[i].value= Integer.parseInt(parts[1]);
                			chance[i].special ='S';
                		}
                		else if (parts[0].compareTo("*")==0){
                			chance[i].special='M';
                			chance[i].value= Integer.parseInt(parts[1]);
                		}
                		else if (parts[0].compareTo("P")==0){
                			chance[i].special='P';
                			chance[i].value=0;
                		}
                		else if (parts[0].compareTo("L")==0){
                			chance[i].special='L';
                			chance[i].value=0;
                		}
                	}
                	
                	for(int i = 0; i < chanceSize; i++){
                		System.out.println(chanceImagePaths[i]);
                	}
                }
                
                if(line.compareTo("# PATH OF EACH COMPANY CARD") == 0) {
                	line = reader.readLine();
                	String mainFolders = line;
                	companyImagePaths= new String[companySize];
              
                	String parts[];
                	line = reader.readLine();
                	for(int i = 0; i < companySize; i++){
                		companyImagePaths[i] = mainFolders + line;
                		company[i].imagePath=companyImagePaths[i];
                		line = reader.readLine();
                		parts= line.split(" ");
                		company[i].multiplier= Integer.parseInt(parts[0]);
                		company[i].mortgage= Integer.parseInt(parts[1]);
                	}
                	
                	/*for(int i = 0; i < companySize; i++){
                		System.out.println(companyImagePaths[i]);
                	}*/
                }
                
                if(line.compareTo("# PATH OF EACH TERRAIN CARD") == 0) {
                	line = reader.readLine();
                	String mainFolders = line;
                	terrainImagePaths= new String[terrainSize];
                	String parts[];
                	line = reader.readLine();
                	for(int i = 0; i < terrainSize; i++){
                		terrainImagePaths[i] = mainFolders + line;
                		terrain[i].imagePath= terrainImagePaths[i];
                		line = reader.readLine();
                		parts = line.split(" ");
                		terrain[i].rent = Integer.parseInt(parts[0]);
                		terrain[i].house[0] = Integer.parseInt(parts[1]);
                		terrain[i].house[1] = Integer.parseInt(parts[2]);
                		terrain[i].house[2] = Integer.parseInt(parts[3]);
                		terrain[i].house[3] = Integer.parseInt(parts[4]);
                		terrain[i].hotel = Integer.parseInt(parts[5]);
                		terrain[i].houseCost = Integer.parseInt(parts[6]);
                		terrain[i].houseCost = Integer.parseInt(parts[7]);
                		terrain[i].mortgage = Integer.parseInt(parts[8]);
                	}
                	
                	/*for(int i = 0; i < terrainSize; i++){
                		System.out.println(terrainImagePaths[i]);
                	}*/
                }
                
                if(line.compareTo("# PATH OF EACH PIN") == 0) {
                	line = reader.readLine();
                	String mainFolders = line;
                	pinImagePaths= new String[pinSize];
                	
                	line = reader.readLine();
                	for(int i = 0; i < pinSize; i++){
                		pinImagePaths[i] = mainFolders + line;
                		line = reader.readLine();
                	}
                	
                	/*for(int i = 0; i < pinSize; i++){
                		System.out.println(pinImagePaths[i]);
                	}*/
                }
                
                line = reader.readLine();
            } 

			
		} catch (FileNotFoundException e){
			System.out.println("ERROR: File could not be found.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void load(File dataFile) {
		
		try {
			readFile(dataFile);

			// Load single images
			dice1 = ImageIO.read(new File("src/res/dice_sheet.png"));
			dice2 = ImageIO.read(new File("src/res/dice_sheet.png"));
			board = ImageIO.read(new File("src/res/table.png"));
			
			// Load arrays of images
			chanceCards = new Image[chanceSize];
			companyCards = new Image[companySize];
			terrainCards = new Image[terrainSize];
			pins = new Image[pinSize];
			
			for(int i = 0; i < chanceSize; i++) {
				chanceCards[i] = ImageIO.read(new File(chanceImagePaths[i]));
			}
			
			for(int i = 0; i < companySize; i++) {
				companyCards[i] = ImageIO.read(new File(companyImagePaths[i]));
			}
			
			for(int i = 0; i < terrainSize; i++) {
				terrainCards[i] = ImageIO.read(new File(terrainImagePaths[i]));
			}
			
			for(int i = 0; i < pinSize; i++) {
				pins[i] = ImageIO.read(new File(pinImagePaths[i]));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public static final void main(String[] args) {
		ImageLoader i = new ImageLoader();
		File dataFile = new File("src/res/data.config");
		
		i.load(dataFile);
	}*/
}