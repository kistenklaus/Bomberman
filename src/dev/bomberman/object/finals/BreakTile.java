package dev.bomberman.object.finals;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.bomberman.object.ID;
import dev.bomberman.object.Tile;


public final class BreakTile extends Tile{
	
	private static BufferedImage img = loadImage("Tron_Liquid_Mark_I.png", Tile.TILE_W, Tile.TILE_H);
	
	public BreakTile(float posX, float posY) {
		super(posX, posY, ID.BreakTile);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int)posX, (int)posY, null);
		//g.setColor(Color.WHITE);
		//g.drawRect((int)posX, (int)posY, width, height);
	}

}
