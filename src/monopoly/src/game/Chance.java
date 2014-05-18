package monopoly.src.game;

import java.awt.Image;

public class Chance {
	char function;	// What this card does
	int value;		// The value for whatever it does
	boolean keep;	// If the player keep it or goes back to pile
	Image image;
	
	public Chance(char function, int value, Image image) {
		this.function = function;
		this.value = value;
		this.keep = keep;
		this.image = image;
	}
}
