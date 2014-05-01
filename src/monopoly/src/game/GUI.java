package monopoly.src.game;
//import java.awt.image.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;

//import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class implements all methods related to the Graphic User Interface.
 *
 */

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	JButton test = new JButton("Oh!");
	GUI_PANEL mainPanel= new GUI_PANEL();
	
	public GUI(String screenBarName,int width, int height) {
		/* Default Construction*/
		Toolkit userConfig = Toolkit.getDefaultToolkit();
		Dimension userScreenResolution = userConfig.getScreenSize();
		setBounds(((userScreenResolution.width)/2)-(width/2),((userScreenResolution.height)/2)-(height/2), width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(screenBarName);
		setVisible(true);
		mainPanel.add(test);
		getContentPane().add(mainPanel);
		
	}


}
 class GUI_PANEL extends JPanel{
 private static final long serialVersionUID = 1L;
 
 public void paintComponent(Graphics g){
	 super.paintComponent(g);
	 
 }
	
}