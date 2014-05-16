package monopoly.src.game;

abstract class Property {
	protected int ownerID;
	
	protected final String name;
	protected final int price;
	protected int rent;
	protected final int mortgage;
	
	public Property(String name, int price, int rent, int mortgage) {
		ownerID = -1;	// All properties belong to the bank at beginning
		
		this.name = name;
		this.price = price;
		this.rent = rent;
		this.mortgage = mortgage;
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
	
	public int getPropertyRent() {
		return rent;
	}
	
	public void setPropertyRent(int rent) {
		this.rent = rent;
	}
	
	public int getPropertyOwnerID() {
		return ownerID;
	}
	
	public void setPropertyOwner(int ownerID) {
		this.ownerID = ownerID;
	}
}
