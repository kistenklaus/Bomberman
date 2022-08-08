package graphic_furface;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import core.Lobby;
import evnt_managment.KeyEvtManager;
import evnt_managment.MouseEvtManager;

@SuppressWarnings("serial")
public class Graphic_user_interface extends JFrame implements Runnable{

	private BufferStrategy strat;
	
	public Graphic_user_interface(int w, int h) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(w, h);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		addKeyListener(new KeyEvtManager());
		addMouseListener(new MouseEvtManager());
		addMouseMotionListener(new MouseEvtManager());
		
		makeStrat();
	}
	public void run() {
		for(;;){
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {e.printStackTrace();}
			repaintScreen();
			
		}
	}
	public void makeStrat(){
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}
	public void repaintScreen(){
		Graphics g = strat.getDrawGraphics();
		draw(g);
		g.dispose();
		strat.show();
	}
	
	private void draw(Graphics g){
		if(Lobby.avaiable){
			Lobby.game.field.draw(g);
			for (int i = 0; i < Lobby.game.figures.length; i++) {
				Lobby.game.figures[i].draw(g);
			}
		}
		
	}

}
