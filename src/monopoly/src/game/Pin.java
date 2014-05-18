package monopoly.src.game;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Pin {
 public Color color;
 public BufferedImage img;
  Pin (String color, BufferedImage image){
	 this.img=image;
	 this.color = Color.getColor(color);
  }
}
