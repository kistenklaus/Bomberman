package dev.bomberman.core;

import java.awt.Canvas;
import java.util.List;

import dev.bomberman.constants.KONSTANT;
import dev.bomberman.constants.SET;
import dev.bomberman.object.ID;
import dev.bomberman.object.TickObject;
import dev.bomberman.object.Tile;
import dev.bomberman.object.finals.Player;
import dev.bomberman.object.finals.Space;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -110673391003050531L;
	
	private boolean running = false;
	private Thread ticking;
	private int tps;
	private ObjHandler handler;
	private Field field;
	
	public Game(){
		setLocation(0, 0);
		setSize(KONSTANT.GAMEWIDTH, KONSTANT.GAMEHEIGHT);
		setFocusable(false);
		setVisible(true);
		
		handler = new ObjHandler();
		ticking = new Thread(this);
		handler.addTickObj(new Space(0, 0, KONSTANT.GAMEWIDTH, KONSTANT.GAMEHEIGHT, ID.Background));
		handler.addTickObj(new Player(Tile.TILE_W,Tile.TILE_H,handler));
		field = new Field(handler);
		field.create(0);
		System.out.println("init - game");
	}
	
	public synchronized void start(){
		running = true;
		ticking.start();
	}
	public synchronized void stop(){
		running = false;
		try {
			ticking.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = SET.TICKRATE;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int ticks = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta>=1){
				tick();
				delta--;
				ticks++;
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				tps=ticks;
				ticks=0;
			}
		}
		stop();
	}

	private synchronized void tick() {
		List<TickObject> temp = handler.tickObj;
		for(int i=0; i<temp.size(); i++){
			temp.get(i).tick();
		}
	}
	
	public int getTPS(){return tps;}
	public ObjHandler getObjHandler(){return handler;}
}
