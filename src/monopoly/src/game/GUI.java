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
	private int width=800;
	private int height=600;
	JButton test = new JButton("Oh!");
	JLayeredPane baseLayer = new JLayeredPane();
	private GUI_PANEL mainPanel = new GUI_PANEL();
	private GUI_PANEL [] dice = new GUI_PANEL[2];
	private GUI_PANEL hudOptions = new GUI_PANEL();
	private GUI_PANEL [] players = new GUI_PANEL[6];
	
	public GUI(String screenBarName,int width, int height) {
		/*Attribution */
		this.width=width;
		this.height=height;
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
			
		/*Loads the Panel of the Board*/
		mainPanel.setBackground(Color.darkGray);
		mainPanel.add(test);
		mainPanel.setBounds(0, 0, width-8, 400);
		mainPanel.setOpaque(true);
		baseLayer.add(mainPanel,9);
		//getContentPane().add(mainPanel,basicLayout.SOUTH,0);
		
	}
	public void loadHudOptions() {
		hudOptions.setBackground(Color.BLUE);
		hudOptions.setBounds(0, height-158,width-8, 120);
		hudOptions.setOpaque(true);
		baseLayer.add(hudOptions,2);
		
	}
	public void loadDice(){
		dice[0] =new GUI_PANEL();
		dice[1] =new GUI_PANEL();
		dice[0].setBackground(Color.BLACK);
		dice[1].setBackground(Color.WHITE);
		dice[0].setBounds((width/2)-110, (height/2)-50, 100, 100);
		dice[1].setBounds((width/2)+10, (height/2)-50, 100, 100);
		dice[0].setOpaque(true);
		dice[1].setOpaque(true);
		baseLayer.add(dice[0],0);
		baseLayer.add(dice[1],1);
		
	}
	public void loadPlayers(int amount){
		players[0] = new GUI_PANEL();
		players[1] = new GUI_PANEL();
		players[2] = new GUI_PANEL();
		players[3] = new GUI_PANEL();
		players[4] = new GUI_PANEL();
		players[5] = new GUI_PANEL();
		
		players[0].setBackground(Color.RED);
		players[1].setBackground(Color.BLACK);
		players[2].setBackground(Color.YELLOW);
		players[3].setBackground(Color.BLUE);
		players[4].setBackground(Color.PINK);
		players[5].setBackground(Color.ORANGE);
		
		players[0].setBounds(0  , 124, 166, 65);
		players[1].setBounds(858, 124, 166, 65);
		players[2].setBounds(0  , 334, 166, 65);
		players[3].setBounds(858, 334, 166, 65);
		players[4].setBounds(0  , 543, 166, 65);
		players[5].setBounds(858, 543, 166, 65);

		players[0].setOpaque(true);
		players[1].setOpaque(true);
		players[2].setOpaque(true);
		players[3].setOpaque(true);
		players[4].setOpaque(true);
		players[5].setOpaque(true);
		int counter =0;
		if (amount>6)
			amount=6;
		else if (amount<2)
			amount=2;
		while(amount!=0){
			baseLayer.add(players[counter],3+counter);
			amount--;
			counter++;
			
		}
			
		
			
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