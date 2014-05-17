package monopoly.src.game;

import java.security.acl.Owner;

public class Company extends Property {
	
	public Company(String name, int price, int rent, int mortgage, int location) {
		super(name, price, rent, mortgage, location);
	}

	/**diceValue  is the dice value to be multiplied by this company's rent */
	public int getPropertyRent(Integer diceValue) {
		
		return rent * diceValue ;
	}


}
