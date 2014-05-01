package monopoly.src.game;

public class Game {
	public static void main(String args[]) {
		GUI mainSreen = new GUI("Minha Tela",1024,768);
		System.out.print("oi \n");
		mainSreen.loadHudOptions();
		mainSreen.loadBoard();
		mainSreen.loadDice();
		
		mainSreen.loadPlayers(2);
	}
}
