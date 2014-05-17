package monopoly.src.game;

public class Player {
	private int id;
	private int money;
	private int position;	// In terms of spaces
	private String name;
	
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
	
	public int getMoney() {
		return money;
	}
	
	public void addMoney(int plusAmount) {
		money += plusAmount;
	}
	
	public void chargeMoney(int minusAmount) {
		money -= minusAmount;
	}
	
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
		p.ownerID = id;
	}
	
	public void sellProperty() {
		
	}

}


