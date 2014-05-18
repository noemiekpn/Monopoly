package monopoly.src.game;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Terrain extends Property {
	private int numHouses = 0;
	private int numHotels = 0;
    private Integer rentWithConstruction[]= new Integer[5];
    private int toConstruct;
	public Terrain(String name, int price, int rent, int mortgage, int location, BufferedImage img, Integer []cost, int construtction) {
		super(name, price, rent, mortgage, location, img);
		rentWithConstruction = cost.clone();
		toConstruct= construtction;
	}
	
	public void addHouses(int numHouses) {
		int total = this.numHouses + numHouses;
		
		if(total > 4) {
			// Terrain is ready for hotel
			int ans = JOptionPane.showConfirmDialog(null,
					"You already have 4 houses here.\n" + 
					"Do you want to add a hotel instead?", 
					"House Limit Warning",
					JOptionPane.YES_NO_OPTION);
			
			if(ans == JOptionPane.YES_OPTION) {
				addHotels(1);
			}
			
		} else {
			this.numHouses += numHouses;
		}
	}
	
	public void addHotels(int numHotels) {
		int total = this.numHotels + numHotels;
		
		if(total > 0) {
			// Terrain is at full limit
			JOptionPane.showMessageDialog(null, "You already have 4 hotels here.\n");
		} else {
			this.numHotels += numHotels;
		}
	}

	public int getPropertyRent() {
		if (numHotels==0){
			if(numHouses!=0)
				return rentWithConstruction[numHouses];
			else
				return rent;
		}
		else{
			return rentWithConstruction[5];
		}

	}
	

}
