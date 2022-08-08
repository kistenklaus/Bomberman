package core;


/**
 * the Lobby class is just a class to controll the Game and the Menue Settings
 * not Complet
 * @author karl
 *
 */
public class Lobby implements Runnable{
	
	public static Game game;
	public static boolean avaiable = false;
	
	public Lobby(){
		
	}
	
	@Override
	public void run() {
		for(;;){
			game = new Game(1,1);
			avaiable=true;
			game.play();
			avaiable=false;
			System.out.println("asd");
		}
	}
	
}
