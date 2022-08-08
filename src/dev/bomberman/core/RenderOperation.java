package dev.bomberman.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;

import dev.bomberman.constants.SET;
import dev.bomberman.object.GameObject;


public class RenderOperation implements Runnable{
	
	private boolean running;
	private Thread rendering;
	
	private Game game;
	private ObjHandler handler;
	
	public RenderOperation(Game game) {
		rendering = new Thread(this);
		this.game=game;
		handler =game.getObjHandler();
		System.out.println("init --- RenderOp");
		
	}
	
	public synchronized void start(){
		running = true;
		rendering.start();
	}
	public synchronized void stop(){
		running = false;
		try {
			rendering.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long timer2 = timer;
		int frames = 0;
		while(running){
			if(System.currentTimeMillis()-timer2>1000/SET.FPSCAP){
				timer2 += 1000/SET.FPSCAP;
				render();
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(String.format("Renderd: FPS"+"|%6d|"+"  Ticks: TPS"+"|%4d|", frames,game.getTPS()));
				frames=0;
			}
		}
		stop();
		
	}

	private void render() {
		BufferStrategy bs = game.getBufferStrategy();
		if(bs == null){
			game.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		
		List<GameObject> temp = handler.gameObj;
		for(int i=0; i<temp.size(); i++){
			try {
				temp.get(i).render(g);
			} catch (Exception e) {System.out.println("ERROR: synch based, in the renderOperation");}
		}
		
		
		g.dispose();
		bs.show();

	}

}
