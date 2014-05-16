package monopoly.src.game;

public class Terrain extends Property {
	private int numHouses = 0;
	private int numHotels = 0;

	public Terrain(String name, int price, int rent, int mortgage) {
		super(name, price, rent, mortgage);
	}
	
	public void addHouses(int numHouses) {
		int total = this.numHouses + numHouses;
		
		if(total > 4) {
			// Terrain is ready for hotel
			
		} else {
			this.numHouses += numHouses;
		}
	}
	
	public void addHotels(int numHotels) {
		int total = this.numHotels + numHotels;
		
		if(total > 4) {
			// Terrain is at full limit
			/*final JOptionPane p = new JOptionPane("You already have 4 hotels here.\n");*/
		} else {
			this.numHotels += numHotels;
		}
	}
}
