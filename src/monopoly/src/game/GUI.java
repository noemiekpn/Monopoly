package monopoly.src.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * This class implements all methods related to the Graphic User Interface.
 *
 */

public class GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private int width=800;
	private int height=600;
	
	private Point[] boardPositions = new Point[40];
	
	JButton rollButton = new JButton("JOGAR DADOS");
	JButton adminButton = new JButton("ADMINISTRAR PROPRIEDADES");
	JButton offerButton = new JButton("FAZER OFERTA");
	JLayeredPane baseLayer = new JLayeredPane();
	
	private GUI_PANEL mainPanel = new GUI_PANEL();
	private GUI_PANEL [] dice = new GUI_PANEL[2];
	private GUI_PANEL hudOptions = new GUI_PANEL();
	private GUI_PANEL [] players = new GUI_PANEL[6];
	private ImageLoader imgData = new ImageLoader();
	private boolean alreadyRolled = false;
	
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
		File dataConfig;
		try {
				dataConfig= new File("src/res/data.config");
				imgData.load (dataConfig);
			}
		catch(Exception e){	
				e.printStackTrace();
			} 
	}
	
	public void loadBoard() {
		/*Loads the Panel of the Board*/
		mainPanel.setBackground(Color.darkGray);
		mainPanel.setBounds(0, 0, width, height);
		mainPanel.setOpaque(true);
		mainPanel.setImg(imgData.board, 0);
		mainPanel.setImgPos(0, 0, 0, width, height);
		baseLayer.add(mainPanel,new Integer(0), 0);
		//getContentPane().add(mainPanel,basicLayout.SOUTH,0);
	}
	
	public void loadBoardPositions() {
		int i = 0,
			xL = 206, yL = 58,	// Left most coordinates 
			xR = 736, yR = 588; // Right most coordinates
		
		while (i < boardPositions.length) {
			boardPositions[i] = new Point();
				
			if(i >= 0 && i <= 10) {
				boardPositions[i].x = xR;
				
				if(i == 0) {
					boardPositions[i].y = yL;
				} else if(i == 1) {
					boardPositions[i].y = boardPositions[i - 1].y + 80;
				} else {
					boardPositions[i].y = boardPositions[i - 1].y + 50;
				}
			}
			
			if(i >= 11 && i <= 20) {
				boardPositions[i].y = yR;
					
				if(i == 11) {	
					boardPositions[i].x = boardPositions[i - 1].x - 80;
				} else {
					boardPositions[i].x = boardPositions[i - 1].x - 50;
				}
			}
			
			if(i >= 21 && i <= 30) {
				boardPositions[i].x = xL;
					
				if(i == 11) {	
					boardPositions[i].y = boardPositions[i - 1].y - 80;
				} else {
					boardPositions[i].y = boardPositions[i - 1].y - 50;
				}
			}
			
			if(i >= 31 && i < 40) {
				boardPositions[i].y = yL;
					
				if(i == 21) {	
					boardPositions[i].x = boardPositions[i - 1].x + 80;
				} else {
					boardPositions[i].x = boardPositions[i - 1].x + 50;
				}
			}
			
			System.out.println("Position " + i + " = " + boardPositions[i]);
			i++;
		}
	}
	
	public void loadHudOptions() {
		// Set buttons
		rollButton.setActionCommand("roll");
		rollButton.addActionListener(this);
		
		adminButton.setActionCommand("admin");
		rollButton.addActionListener(this);
		
		offerButton.setActionCommand("offer");
		rollButton.addActionListener(this);

		hudOptions.setBounds(0, height-83,width, 45);
		hudOptions.setOpaque(false);
		hudOptions.setBackground(Color.BLUE);
		hudOptions.setBounds(0, height-83,width, 45);
		hudOptions.setOpaque(true);
		
		// Add buttons
		hudOptions.add(rollButton);
		hudOptions.add(adminButton);
		hudOptions.add(offerButton);		
		
		baseLayer.add(hudOptions,new Integer(4), 4);
		
	}
	
	public void loadDice(){
		dice[0] =new GUI_PANEL();
		dice[1] =new GUI_PANEL();
		dice[0].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		dice[1].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		dice[0].setBounds((width/2)-110, (height/2)-50, 100, 100);
		dice[1].setBounds((width/2)+10, (height/2)-50, 100, 100);
		dice[0].setOpaque(true);
		dice[1].setOpaque(true);
		dice[0].setImg(imgData.dice1, 1);
		dice[1].setImg(imgData.dice2, 1);
		dice[0].setImgPos(0, 0, -1, 101, 100, 0, 0, 100, 100);
		dice[1].setImgPos(0, 0, -1, 101, 100, 0, 0, 100, 100);
		baseLayer.add(dice[0],new Integer(1),1);
		baseLayer.add(dice[1],new Integer(2),2);
		
	}
	
	public void loadPlayers(int amount){
		players[0] = new GUI_PANEL();
		players[1] = new GUI_PANEL();
		players[2] = new GUI_PANEL();
		players[3] = new GUI_PANEL();
		players[4] = new GUI_PANEL();
		players[5] = new GUI_PANEL();
		
		players[0].setBackground(new Color(34, 34, 34));
		players[0].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		players[1].setBackground(new Color(193, 39, 45));
		players[1].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		players[2].setBackground(new Color(0, 113, 188));
		players[2].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		players[3].setBackground(new Color(227, 135, 28));
		players[3].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		players[4].setBackground(new Color(94, 59, 120));
		players[4].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		players[5].setBackground(new Color(252, 241, 86));
		players[5].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		
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
			baseLayer.add(players[counter],new Integer(3),3+counter);
			amount--;
			counter++;
			
		}

	}
	
	public void unLoadBoard() {
		getContentPane().remove(0);
		
	}

	public void  updateDice(int first, int second) {
		dice[0].setImgPos(0, 0, -1, 101, 100, 600-(100*first), 0, (600-(100*first)+100), 100);
		dice[1].setImgPos(0, 0, -1, 101, 100, 600-(100*second), 0, (600-(100*second)+100), 100);
		
	}
	/**
	 * Actions to be performed by clicked buttons in hudOptions
	 */
	public void actionPerformed(ActionEvent event) {
		if("roll".equals(event.getActionCommand())) {
			if(!alreadyRolled){
				//rollButton.setEnabled(false);
				int first = (new Dice()).roll();
				int second = (new Dice()).roll();
				updateDice(first, second);
				System.out.println("1: "+first+" 2: "+second);
				getContentPane().repaint();
				//alreadyRolled=true;
			}
			
		}
	}

}

class GUI_PANEL extends JPanel{
private static final long serialVersionUID = 1L;
private boolean hasImg=false;
private int typeOfSet=0;
private Image [] displayedImages;
private int [] posX;
private int [] posY;
private int [] width;
private int [] height;
private int [] dX0;
private int [] dY0;
private int [] dX1;
private int [] dY1;
private int [] sX0;
private int [] sY0;
private int [] sX1;
private int [] sY1;

public void setImg(Image img, int type) {
	 hasImg=true;
	 displayedImages= new Image[1];
	 if (type==0){
		 posX = new int[1];
		 posY = new int[1];
		 width = new int[1];
		 height = new int[1];
	 }
	 else if (type==1){
		dX0 = new int[1];
		dY0 = new int[1];
		dX1 = new int[1];
		dY1 = new int[1];
		sX0 = new int[1];
		sY0 = new int[1];
		sX1 = new int[1];
		sY1 = new int[1];
		 
	 }
	 displayedImages[0]=img;
	
}
public void setImg(Image []img, int type){
	 hasImg=true;
	 typeOfSet=type;
	 displayedImages = new Image[img.length];
	 if (type==0){
		 posX = new int[img.length];
		 posY = new int[img.length];
		 width = new int[img.length];
		 height = new int[img.length];
	 }
	 else if (type==1){
		dX0 = new int[img.length];
		dY0 = new int[img.length];
		dX1 = new int[img.length];
		dY1 = new int[img.length];
		sX0 = new int[img.length];
		sY0 = new int[img.length];
		sX1 = new int[img.length];
		sY1 = new int[img.length];
		 
	 }
	 for(int i=0;i<img.length;i++){
		 displayedImages[i]=img[i];
	 }
}
public void setImgPos(int index, int posX, int posY, int width, int height) {
	 typeOfSet=0;
	 this.posX[index]= posX;
	 this.posY[index]= posY;
	 this.width[index]=width;
	 this.height[index]=height;
}
public void setImgPos(int index, int dX0, int dY0,int dX1, int dY1,int sX0, int sY0,int sX1, int sY1) {
	 typeOfSet=1;
	 this.dX0[index]=dX0;
	 this.dY0[index]=dY0;
	 this.dX1[index]=dX1;
	 this.dY1[index]=dY1;
	 this.sX0[index]=sX0;
	 this.sY0[index]=sY0;
	 this.sX1[index]=sX1;
	 this.sY1[index]=sY1;
}
private void drawImgs1(Graphics g){
	for(int i=0; i< displayedImages.length;i++){
		g.drawImage(displayedImages[i], posX[i], posY[i], width[i], height[i], null);
		
	}
}
private void drawImgs2(Graphics g ) {
	for(int i=0; i< displayedImages.length;i++){
		g.drawImage(displayedImages[i], dX0[i],dY0[i],dX1[i],dY1[i],sX0[i],sY0[i],sX1[i],sY1[i],null);
	}	
	
}
public void paintComponent(Graphics g){
	 super.paintComponent(g);
	 if (hasImg){
		 if(typeOfSet==0)
			 drawImgs1(g);
		 else
			 drawImgs2(g);
	 }
}
	
}
