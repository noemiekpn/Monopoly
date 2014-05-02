package monopoly.src.game;

public class Player {
	public int id;
	public int position;
	public String name;
	
	public Player(int playerID, String playerName) {
		id = playerID;
		position = 0;
		name = playerName;
	}
}
