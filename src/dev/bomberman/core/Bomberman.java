package dev.bomberman.core;


public class Bomberman {
	
	private static Game game;
	private static RenderOperation renderOp;
	
	public static void main(String[] args) {
		init();
		start();
	}
	
	private static void init(){
		game = new Game();
		renderOp = new RenderOperation(game);
		Menue menue = new Menue();
		new Window(game ,menue,"Gamecore:Mulitcore v1");
	}
	
	private static void start(){
		game.start();
		renderOp.start();
	}
	
}
