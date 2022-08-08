package dev.bomberman.core;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import dev.bomberman.evntmanagement.KeyEvntHandler;
import dev.bomberman.evntmanagement.MouseEvntHandler;

public class Window extends Canvas{
	
	public static final int WIDTH = 640, HEIGHT = (int)(WIDTH*0.5625d);
	
	private static final long serialVersionUID = 2314198144610048199L;
	
	public Window(Game game,Menue menue, String name){
		JFrame frame = new JFrame(name);
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.add(game);
		frame.add(menue);
		
		
		
		frame.addKeyListener(new KeyEvntHandler(game.getObjHandler()));
		
		MouseEvntHandler mouse = new MouseEvntHandler();
		frame.addMouseListener(mouse);
		frame.addMouseMotionListener(mouse);
		frame.addMouseWheelListener(mouse);
		
		
		System.out.println("init--- Window");
		
		frame.setVisible(true);
	}
}
