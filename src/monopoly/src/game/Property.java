package monopoly.src.game;
import monopoly.src.game.Player;
abstract class Property {

	protected Player ownerID;
	
	protected final int location; 	// Position in board
	
	
	protected final String name;
	protected final int price;
	protected int rent;
	protected final int mortgage;
	
	public Property(String name, int price, int rent, int mortgage, int location) {
		ownerID =null;	// All properties belong to the bank at beginning
		
		this.name = name;
		this.price = price;
		this.rent = rent;
		this.mortgage = mortgage;
		this.location = location;
	}
		
	public String getPropertyName() {
		return name;
	}
	
	public int getPropertyPrice() {
		return price;
	}

	public int getPropertyMortgage() {
		return mortgage;
	}
	
	public Player getPropertyOwnerID() {
		return null;
	}
	
	public void setPropertyOwner(Player ownerID) {
		this.ownerID = ownerID;
	}
}
