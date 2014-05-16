package monopoly.src.game;

public class Player {
	private int id;
	private int position;
	private String name;
	
	private int doublesCounter;
	
	public Player(int playerID, String playerName) {
		id = playerID;
		name = playerName;
		position = 0;
		doublesCounter = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public int getID(){
		return id;
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
		
	}
	
	public void sellProperty() {
		
	}

}


