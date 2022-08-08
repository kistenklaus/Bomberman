package core;


import graphic_furface.Graphic_user_interface;

/**
 * Dieses Programm soll Bomberman nachstellen selbstprogrammiert auf Java
 * die Graphics sind hauptsächlich von Luca Winkler produziert
 * @author karl
 */
public class Bomberman {
	/**
	 * main Methode
	 * Organieses the Games
	 * @param args is the Thread
	 */
	public static void main(String[] args) {
		Thread game = new Thread(new Lobby());
		game.start();
		
		
		
		Thread updInterface = new Thread(new Graphic_user_interface(STATS.WINDOW_W, STATS.WINDOW_H));
		updInterface.start();
	}
}









































