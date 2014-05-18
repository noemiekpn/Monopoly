package monopoly.src.game;
 
import java.awt.Image;
import java.awt.image.BufferedImage;

 
public class Company extends Property {
 
        public Company(String name, int price, int rent, int mortgage, int location, BufferedImage img) {
                super(name, price, rent, mortgage, location, img);
        }
 
        /**
         *
         * @param diceValue - rolled value to be multiplied by this company's rent.
         * @return Returns final property rent.
         */
        public int getCompanyRent(int diceValue) {
                return rent * diceValue ;
        }
}