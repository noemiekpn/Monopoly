package monopoly.src.game;

public class Game {
	public static void main(String args[]) {
		GUI mainSreen = new GUI("Minha Tela",1024,768);
		mainSreen.loadBoard();
		System.out.print("oi");
		mainSreen.hudOptions();
	}
}
