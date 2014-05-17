package monopoly.src.game;

public class Company extends Property {
	private final int diceWeight;
	
	public Company(String name, int price, int rent, int mortgage, int diceWeight, int location) {
		super(name, price, rent, mortgage, location);
		this.diceWeight = diceWeight;
	}
	
}
