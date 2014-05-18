package monopoly.src.game;

public class Game {
	public static void main(String args[]) {
		int numPlayers = 3;
		
		GUI mainScreen = new GUI("Monopoly", 1024, 768);
		mainScreen.loadHudOptions();
		mainScreen.loadDice();
		mainScreen.loadPlayers(numPlayers);
		mainScreen.loadBoard();
		mainScreen.loadBoardPositions();
		mainScreen.loadInfo();
	}
}
