package monopoly.src.game;
import java.awt.Image;

import monopoly.src.game.Player;
abstract class Property {

	protected Player ownerID;
	
	protected final int location; 	// Position in board
	
	
	protected final String name;
	protected final int price;
	protected int rent;
	protected final int mortgage;
	protected final Image img;
	
	public Property(String name, int price, int rent, int mortgage, int location, Image img) {
		ownerID =null;	// All properties belong to the bank at beginning
		
		this.name = name;
		this.price = price;
		this.rent = rent;
		this.mortgage = mortgage;
		this.location = location;
		this.img=img;
	}
	/**Tells the property'name*/	
	public String getPropertyName() {
		return name;
	}
	/**Returns the buying price */
	public int getPropertyPrice() {
		return price;
	}
     /**Returns the mortgage's price  */
	public int getPropertyMortgage() {
		return mortgage;
	}
	/**Tells who's the owner */
	public Player getPropertyOwnerID() {
		return null;
	}
	/**Set new ownership */
	public void setPropertyOwner(Player ownerID) {
		this.ownerID = ownerID;
	}
	/**Returns the property location inside of the table*/
	public int getLocation(){
		return location;
	}
	public void drawCard(GUI_PANEL Pane){
		Pane.removeAll();
		Pane.setImg(img, 0);
		Pane.setImgPos(0, 0, 0);
		
		
	}
}
