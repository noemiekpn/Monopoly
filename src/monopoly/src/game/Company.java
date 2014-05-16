package monopoly.src.game;

public class Company extends Property {
	private final int diceWeight;
	
	public Company(String name, int price, int rent, int mortgage, int diceWeight) {
		super(name, price, rent, mortgage);
		this.diceWeight = diceWeight;
	}
	
}
