package monopoly.src.game;
import java.awt.Image;
public class Company extends Property {
	
	public Company(String name, int price, int rent, int mortgage, int location, Image img) {
		super(name, price, rent, mortgage, location, img);
	}

	/**diceValue  is the dice value to be multiplied by this company's rent */
	public int getPropertyRent(Integer diceValue) {
		
		return rent * diceValue ;
	}


}
