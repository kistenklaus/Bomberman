package dev.bomberman.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Item extends TickObject{
	
	
	public Item(float posX, float posY, float width, float height, ID id) {
		super(posX, posY, width, height, id);
	}
	
	protected void drawBounding(Graphics g){
		g.setColor(Color.ORANGE);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(bounding);
	}

}
