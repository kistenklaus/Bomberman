package dev.bomberman.core;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import dev.bomberman.constants.KONSTANT;


public class Menue extends JPanel{
	
	private static final long serialVersionUID = 8471600259511189724L;
	
	private int tx,ty;
	private int width, height;
	
	public Menue() {
		super(null,true);
		tx=KONSTANT.GAMEWIDTH;
		ty=0;
		width=KONSTANT.MENUEWIDTH; height = KONSTANT.MENUEHEIGHT;
		setBackground(Color.GRAY);
		setVisible(true);
		setFocusable(false);
		
		JButton bg = new JButton();
		bg.setBounds(tx, ty, width, height);
		bg.setFocusable(false);
		bg.setVisible(true);
		
		add(bg);
		System.out.println("init --- Menue");
	}
	
}
