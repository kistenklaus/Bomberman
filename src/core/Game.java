package core;

import java.awt.Rectangle;

import field.Field;
import players.Computer;
import players.Figure;
import players.Player;

/**
 * can be init and runned after the game ends the play-Methode goes on and the Thread continues
 * 
 * includes a kill methode witch is terminating a figure 
 * from the game with the index of the figure in the figure array(in this class)
 * 
 * includes player_intersect gives back the index of the Figure witch has intersected with the @parm obj<Rectangle> 
 * @author karl
 */
public class Game extends Lobby{
	/**
	 * field is the Field of the Game
	 */
	public Field field;
	/**
	 * figures includes all figures in the Game
	 * is only init cause of the interface Thread
	 */
	public Figure[] figures = new Figure[0];
	
	private int nPlayer_alive;
	
	public Game(int nPlayer, int nAI){
		field = new Field(this);
		figures = new Figure[nPlayer+nAI];
		nPlayer_alive=nPlayer;
		
		/*
		 * Optimize Pls so that the player spawn in the right pos
		 * whem more than 2
		 */
		figures[0] = new Player(1,1,field);
		figures[1] = new Computer(13,13,field);
	}
	
	public void play(){
		long lf = System.currentTimeMillis();
		while(nPlayer_alive>0){
			long tf = System.currentTimeMillis();
			double delay = (tf-lf)/1000f;
			lf=tf;
			for (int i = 0; i < figures.length; i++) {
				figures[i].update(delay);
			}
			field.update(delay);
			
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		figures = null;
		field = null;
	}
	/**
	 * look up at the class discribtion
	 * @param obj
	 * @return
	 */
	public int player_intsect(Rectangle obj){
		for(int f=0;f<figures.length; f++){
			if(figures[f].getHitbox().intersects(obj)){
				return f;
			}
		}
		return -1;
	}
	/**
	 * look up at the class discibtion
	 * @param f_index
	 */
	public void kill(int f_index){
		figures[f_index].setAlive(false);
		if(figures[f_index].isPlayable()){
			nPlayer_alive--;
		}
	}
}
