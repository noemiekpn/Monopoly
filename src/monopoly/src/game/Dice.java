package monopoly.src.game;

import java.util.Random;

/**
 * This class implements methods for a 6 faced dice.
 * 
 */
public class Dice {
	Random dice = new Random();
	
	public int roll() {
		int number = dice.nextInt(6) + 1;
		return number;
	}
	
	/**
	 * Dice images are in a sprite sheet 600px wide and 100px high.
	 * Each face of the dice is a 100px square from left the right.
	 * Thus, to display a certain face, position in image will be
	 * updated. This function returns the x position, for the y position, 
	 * due to the image, will always be 0.
	 * 
	 * The x position will depend on the current roll() call, being
	 * set by the function:
	 * 
	 * posX = (roll() - 1) * 100;
	 * 
	 * @author Noemie
	 */
	
	/*public static final void main(String[] args) {
		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		
		for(int i = 0; i < 100; i++) {
			System.out.println("Player rolled " + dice1.roll() + " and " + dice2.roll());
		}
	}*/
}
