package monopoly.src.game;

public class Player {
	private int id;
	private int money;
	private int position;	// In terms of spaces
	private String name;
	private boolean isReceiving = true;
	private int doublesCounter;
	
	public Player(int playerID, String playerName) {
		id = playerID;
		name = playerName;
		
		money = 8 * 1 + 10 * 5 + 10 * 10 + 10 * 50 + 8 * 100 + 2 * 500;
		position = 0;
		doublesCounter = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public int getID(){
		return id;
	}
	/** tells player's balance */
	public int getMoney() {
		return money;
	}
	/**Add money to players' wallet*/
	public void addMoney(int plusAmount) {
		money += plusAmount;
	}
	/**Subtract player's money */
	public void chargeMoney(int minusAmount) {
		money -= minusAmount;
	}
	/** returns the position of the pin's player in terms of the blocks*/
	public int getPosition(){
		return position;
	}
	
	public void setPosition(int newPosition){
		position = newPosition;
	}
	
	public int getDoublesCounter(){
		return doublesCounter;
	}
	
	public void incrementDoublesCounter(){
		doublesCounter++;
	}
	
	public void resetDoublesCounter(){
		doublesCounter = 0;
	}
	
	public void buyProperty(Property p) {
		p.setPropertyOwner(this);
	}
	
	public void sellProperty() {
		
	}
	/**Set whether player can receive money or not*/
	public void setReceiving(boolean value){
		isReceiving =value;
	}
	/**Tells whether player can receive money or not*/
	public boolean getReceiving (){
		
		return isReceiving;
	}

}


