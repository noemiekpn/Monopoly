package monopoly.src.game;
//import java.awt.image.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;

//import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * This class implements all methods related to the Graphic User Interface.
 *
 */

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	JButton test = new JButton("Oh!");
	JLayeredPane baseLayer = new JLayeredPane();
	GUI_PANEL mainPanel= new GUI_PANEL();
	
	public GUI(String screenBarName,int width, int height) {
		/* Default Construction*/
		Toolkit userConfig = Toolkit.getDefaultToolkit();
		Dimension userScreenResolution = userConfig.getScreenSize();
		setBounds(((userScreenResolution.width)/2)-(width/2),((userScreenResolution.height)/2)-(height/2), width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(screenBarName);
		setVisible(true);
		getContentPane().add(baseLayer, BorderLayout.CENTER);
		
	}
	
	public void loadBoard() {
			
		/*Carrega o Panel do Board*/
		mainPanel.setBackground(Color.darkGray);
		mainPanel.add(test);
		mainPanel.setBounds(0, 0, 1016, 400);
		mainPanel.setOpaque(true);
		baseLayer.add(mainPanel,0);
		//getContentPane().add(mainPanel,basicLayout.SOUTH,0);
		
	}
	public void hudOptions() {
		GUI_PANEL outro = new GUI_PANEL();
		outro.setBackground(Color.BLUE);
		outro.setBounds(0, 648,1016, 120);
		outro.setOpaque(true);
		baseLayer.add(outro,1);
		
	}
	public void unLoadBoard() {
		getContentPane().remove(0);
		
	}

}
 class GUI_PANEL extends JPanel{
 private static final long serialVersionUID = 1L;
 
 public void paintComponent(Graphics g){
	 super.paintComponent(g);
	 
 }
	
}