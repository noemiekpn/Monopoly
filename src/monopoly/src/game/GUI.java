package monopoly.src.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * This class implements all methods related to the Graphic User Interface.
 *
 */
public class GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final int width;
	private final int height;
	
	// Board
	private final int xL = 206, yL = 58,	// Upper left coordinate of board
					  xR = 736, yR = 588; 	// Bottom right coordinate board
	private Point[] boardPositions = new Point[40];		// In term of pixels
	private Point[] pinsOffsets = new Point[6];
	
	// Panels
	JLayeredPane baseLayer = new JLayeredPane();
	private GUI_PANEL mainPanel = new GUI_PANEL();
	private GUI_PANEL[] dice = new GUI_PANEL[2];
	private GUI_PANEL hudOptions = new GUI_PANEL();
	private GUI_PANEL[] players = new GUI_PANEL[6];
	private GUI_PANEL  info = new GUI_PANEL();
	
	private CardLoader cardData = new CardLoader();
	// Panel elements
	JButton rollButton = new JButton("JOGAR DADOS");
	JButton adminButton = new JButton("ADMINISTRAR PROPRIEDADES");
	JButton offerButton = new JButton("FAZER OFERTA");
	JButton endTurn = new JButton("FIM DA JOGADA");

	JButton pass = new JButton("PASSAR VEZ");
	JButton buy = new JButton("COMPRAR");
	JButton admin = new JButton("ADMINISTRAR");
	JButton exchange = new JButton("TROCAR");
	JLabel[] playersMoney = new JLabel[6];
	
	// Player control
	//private int numPlayers; esqueceu que existe .lenght?
	private Player[] playersThisSession;
	private Property properties[] = new Property[36];
	private boolean alreadyRolled = false;
	int whoseTurn = 0;
	
	/** Layer index control*/
	 enum layerIndex{
		 MAINPANEL, HUDOPTIONS, DICE1, DICE2, INFO, PLAYER
	}
	
	
	// Cards
	/********************************************************************************/
	
	public GUI(String screenBarName, int width, int height) {
		/*Attribution */
		this.width = width;
		this.height = height;
		/* Default Construction*/
		Toolkit userConfig = Toolkit.getDefaultToolkit();
		Dimension userScreenResolution = userConfig.getScreenSize();
		
		setBounds(((userScreenResolution.width)/2)-(width/2),((userScreenResolution.height)/2)-(height/2), width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false);
		setTitle(screenBarName);
		setVisible(true);
		getContentPane().add(baseLayer, BorderLayout.CENTER);
		
		File dataConfig;
		try {
			dataConfig = new File("src/res/data.config");
			cardData.load(dataConfig);
		}
		catch(Exception e) {	
			e.printStackTrace();
		} 
	}
	
	/**
	 * This methods sets all possible positions of a Monopoly board.
	 * It also sets up to 6 pins offsets according to these positions and to number of players.
	 */
	public void loadBoardPositions() {
		int i = 0;
		
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
				boardPositions[i].x = boardPositions[i - 1].x - 50;
			}
			
			if(i >= 21 && i <= 30) {
				boardPositions[i].x = xL;
				
				if(i == 29) {
					boardPositions[i].y = boardPositions[i - 1].y - 80;
				} else {
					boardPositions[i].y = boardPositions[i - 1].y - 50;
				}
			}
			
			if(i >= 31 && i < 40) {
				boardPositions[i].y = yL;
					
				if(i == 31) {	
					boardPositions[i].x = boardPositions[i - 1].x + 80;
				} else {
					boardPositions[i].x = boardPositions[i - 1].x + 50;
				}
			}
			
			/*System.out.println("Position " + i + " = " + boardPositions[i]);*/
			i++;
		}
		
		int n = playersThisSession.length;
		int numPlayers = playersThisSession.length;
		int offX = 0,
			offY = -4;
		while(n != 0) {
			/*System.out.print("Pin " + (numPlayers - n) + " position = ");*/
			pinsOffsets[numPlayers - n] = new Point();
			pinsOffsets[numPlayers - n].x = offX;
			pinsOffsets[numPlayers - n].y = offY;
			/*System.out.print(pinsOffsets[numPlayers - n] + "\n");*/
			
			offX += 12;
			
			if(n == 4) {
				offY += 12;
				offX = 0;
			}
			
			n--;
		}
	}
	
	public void loadBoard() {
		Image[] images = new Image[1 + playersThisSession.length];
		
		int counter = 0;
		int n = playersThisSession.length;
		
		// Load board image
		images[counter] = cardData.board;
		counter++;
		
		// Load pins images according to amount of players
		while(n != 0) {
			images[counter] = cardData.pins[counter - 1];
			n--;
			counter++;
		}
		
		// Load all images to panel
		int index = 0;
		
		mainPanel.setImg(images, 0);
		mainPanel.setImgPos(index, 0, 0);
		index++;
		
		int	startX = xR + 16, startY = yL + 8;
		n = playersThisSession.length;
		
		while(n != 0) {
			mainPanel.setImgPos(index, startX, startY);
			
			startX += 12;
			
			if(n == 4) {
				startY += 12;
				startX = xR + 16;
			}
			
			n--;
			index++;
		}
		
		mainPanel.setBackground(Color.darkGray);
		mainPanel.setBounds(0, 0, width, height);
		mainPanel.setOpaque(true);
		baseLayer.add(mainPanel,new Integer(0), layerIndex.MAINPANEL.ordinal());
	}
	
	public void loadHudOptions() {
		// Set buttons
		rollButton.setActionCommand("roll");
		rollButton.addActionListener(this);
		
		adminButton.setActionCommand("admin");
		adminButton.addActionListener(this);
		
		offerButton.setActionCommand("offer");
		offerButton.addActionListener(this);
		
		endTurn.setActionCommand("end");
		endTurn.addActionListener(this);
		endTurn.setEnabled(false);

		hudOptions.setBounds(0, height-83,width, 45);
		hudOptions.setOpaque(false);
		
		// Add buttons
		hudOptions.add(rollButton);
		hudOptions.add(adminButton);
		hudOptions.add(offerButton);
		hudOptions.add(endTurn);
		
		baseLayer.add(hudOptions,new Integer(layerIndex.HUDOPTIONS.ordinal()), layerIndex.HUDOPTIONS.ordinal());//4
		
	}
	
	public void loadInfo() {
		info.setBackground(Color.orange);
		info.setBounds(287, 139, 449, 449);
		info.setVisible(false);
		baseLayer.add(info, new Integer(layerIndex.INFO.ordinal()),layerIndex.INFO.ordinal());
		
	}
	public void loadDice() {
		dice[0] =new GUI_PANEL();
		dice[1] =new GUI_PANEL();
		dice[0].setBackground(Color.BLACK);
		dice[0].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		dice[1].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		dice[0].setBounds((width/2)-110, (height/2)-50, 100, 100);
		dice[1].setBounds((width/2)+10, (height/2)-50, 100, 100);
		dice[0].setOpaque(true);
		dice[1].setOpaque(true);
		dice[0].setImg(cardData.dice1, 1);
		dice[1].setImg(cardData.dice2, 1);
		dice[0].setImgPos(0, 0, -1, 101, 100, 0, 0, 100, 100);
		dice[1].setImgPos(0, 0, -1, 101, 100, 0, 0, 100, 100);
		baseLayer.add(dice[0],new Integer(layerIndex.DICE1.ordinal()),layerIndex.DICE1.ordinal());
		baseLayer.add(dice[1],new Integer(layerIndex.DICE1.ordinal()),layerIndex.DICE2.ordinal());
		
	}
	
	public void loadPlayers(int amount) {
		//numPlayers = amount;
		
		for(int i = 0; i < players.length; i++) {
			players[i] = new GUI_PANEL();
			players[i].setOpaque(true);
			players[i].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		}
		
		players[0].setBackground(new Color(34, 34, 34));
		players[1].setBackground(new Color(0, 113, 188));
		players[2].setBackground(new Color(227, 135, 28));
		players[3].setBackground(new Color(94, 59, 120));
		players[4].setBackground(new Color(193, 39, 45));
		players[5].setBackground(new Color(252, 241, 86));
		
		players[0].setBounds(0  , 124, 166, 65);
		players[1].setBounds(858, 124, 166, 65);
		players[2].setBounds(0  , 334, 166, 65);
		players[3].setBounds(858, 334, 166, 65);
		players[4].setBounds(0  , 543, 166, 65);
		players[5].setBounds(858, 543, 166, 65);
		
		// Players loading
		playersThisSession = new Player[amount];
		
		int counter = 0;
		if (amount>6)
			amount=6;
		else if (amount<2)
			amount=2;
		while(amount!=0){
			baseLayer.add(players[counter],new Integer(3),3+counter);
			amount--;
			counter++;
		}		
		
		
		for(int i = 0; i < playersThisSession.length; i++) {
			playersThisSession[i] = new Player(i + 1, "Jogador " + (i + 1));
			System.out.println(playersThisSession[i].getName() + " is player " + (i + 1));
		}

		// Display players initial money amount
		for(int i = 0; i < playersThisSession.length; i++) {
			playersMoney[i] = new JLabel("", JLabel.CENTER);
			playersMoney[i].setOpaque(false);
			playersMoney[i].setFont(new Font("arial", Font.BOLD, 20));
			playersMoney[i].setText("$ " + playersThisSession[i].getMoney());
			playersMoney[i].setVerticalTextPosition(JLabel.BOTTOM);
			playersMoney[i].setForeground(new Color(255, 255, 255));
			players[i].add(playersMoney[i]);
		}
		
	}
	
	public void unLoadBoard() {
		getContentPane().remove(0);
	}
	
	public void updateDice(int first, int second) {
		dice[0].setImgPos(0, 0, -1, 101, 100, 600-(100*first), 0, (600-(100*first)+100), 100);
		dice[1].setImgPos(0, 0, -1, 101, 100, 600-(100*second), 0, (600-(100*second)+100), 100);
	}
	
	/**
	 * This function will update the pin position in the board after the player has rolled the dices.
	 * @param playerID - Determines which player has rolled the dices.
	 * @param numDice1 - Determines how much dice 1 rolled.
	 * @param numDice2 - Determines how much dice 2 rolled.
	 */
	public void updatePlayerPinPosition(int playerID, int numDice1, int numDice2) {
		// How many spaces to walk
		int total = numDice1 + numDice2;
		int newPos = playersThisSession[playerID - 1].getPosition() + total;
		
		// Check if new position must loop
		if(newPos >= boardPositions.length) {
			System.out.println("New position was: " + newPos);
			newPos = newPos - boardPositions.length;
			System.out.println("New is now: " + newPos);
		}
		
		// Update pin position in the board
		mainPanel.setImgPos(playerID, boardPositions[newPos].x + pinsOffsets[playerID - 1].x, boardPositions[newPos].y + pinsOffsets[playerID - 1].y);
		
		// Update where player is at in the board
		System.out.print("Player " + playerID + " was moved from position " + playersThisSession[playerID - 1].getPosition());
		playersThisSession[playerID -1].setPosition(newPos) ;
		System.out.print(" to position " + newPos + "\n");

	}
	
	/**
	 * Shows the card correspondent to players current position.
	 */
	public void cardDisplay(int playerID) {
		// Player's current position will be the card's on board.
		int curPos = playersThisSession[playerID - 1].getPosition();
		int card;
		/* Check if it is one of the corner positions*/
		if (curPos==0){
			// do something 
			return;
		}
		else if(curPos==10){
			// do something 
			return;
		}
		else if (curPos==20) {
			// do something 
			return;
		}
		else if (curPos==30) {
			// do something 
			return;
		}
		
		// Check if position has a card
		 for(card=0;card< properties.length;card++){
			 if(properties[card].getLocation() == curPos){
				 break;
			 }
		 }
		 properties[card].drawCard(info);
		 //Buttons of options for this property must be created here
		 //Consult properties[card] for info needed
	}
	
	public void actionPerformed(ActionEvent event) {
		if("roll".equals(event.getActionCommand())) {
			if(!alreadyRolled){
				int first = (new Dice()).roll();
				int second = (new Dice()).roll();
				
				System.out.print("Dice 1: " + first + "\tDice 2: " + second);
				updateDice(first, second);

				
				System.out.println("It's " + playersThisSession[whoseTurn].getName() + " turn!");
				updatePlayerPinPosition(playersThisSession[whoseTurn].getID(), first, second);
				
				// If player rolls doubles, can roll again
				if(first == second) {
					playersThisSession[whoseTurn].incrementDoublesCounter();
					System.out.println("Player rolled doubles " + playersThisSession[whoseTurn].getDoublesCounter() + " times!");
					
					// If player rolls doubles 3 times, goes to jail
					if(playersThisSession[whoseTurn].getDoublesCounter() == 3) {
						playersThisSession[whoseTurn].resetDoublesCounter();
						
						rollButton.setEnabled(false);
						endTurn.setEnabled(true);
						
						alreadyRolled = true;
						whoseTurn++;
					} else {						
						alreadyRolled = false;
					}
				} else {
					rollButton.setEnabled(false);
					endTurn.setEnabled(true);
										
					alreadyRolled = true;
					whoseTurn++;
				}
								
				// Reset turn
				if(whoseTurn == playersThisSession.length)
					whoseTurn = 0;
				System.out.println("Next is " + playersThisSession[whoseTurn].getName());
				
				getContentPane().repaint();
			}

			
		}
		else if ("end".equals(event.getActionCommand())){
			rollButton.setEnabled(true);
			endTurn.setEnabled(false);
			
			alreadyRolled = false;
		}
	}

}

class GUI_PANEL extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean hasImg=false;
	private int typeOfSet=0;			// (0) Full or (1) cropped image
	private Image [] displayedImages;
	private int [] posX;
	private int [] posY;
	/*private int [] width;
	private int [] height;*/
	private int [] dX0;
	private int [] dY0;
	private int [] dX1;
	private int [] dY1;
	private int [] sX0;
	private int [] sY0;
	private int [] sX1;
	private int [] sY1;
	/** Add image; Type 1 for able to crop ou 1 for not */
	public void setImg(Image img, int type) {
		 hasImg=true;
		 displayedImages= new Image[1];
		 if (type==0){
			 posX = new int[1];
			 posY = new int[1];
			 /*width = new int[1];
			 height = new int[1];*/
		 }
		 else if (type==1) {
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

	/** Add vector of images; Type 1 for able to crop ou 1 for not */
	public void setImg(Image []img, int type){
		 hasImg=true;
		 typeOfSet=type;
		 displayedImages = new Image[img.length];
		 if (type==0){
			 posX = new int[img.length];
			 posY = new int[img.length];
			 /*width = new int[img.length];
			 height = new int[img.length];*/
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
	
	/**Index is the index of the Array of Images, type 0 if Array not used*/
	public void setImgPos(int index, int posX, int posY/*, int width, int height*/) {
		 typeOfSet=0;
		 this.posX[index]= posX;
		 this.posY[index]= posY;
		 /*this.width[index]=width;
		 this.height[index]=height;*/
	}
	/**Index is the index of the Array of Images, type 0 if Array not used*/
	
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
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i=0; i< displayedImages.length; i++){
			g2d.drawImage(displayedImages[i], posX[i], posY[i], null);
		}
	}
	
	private void drawImgs2(Graphics g ) {
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i=0; i< displayedImages.length;i++){
			g2d.drawImage(displayedImages[i], dX0[i],dY0[i],dX1[i],dY1[i],sX0[i],sY0[i],sX1[i],sY1[i],null);
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


