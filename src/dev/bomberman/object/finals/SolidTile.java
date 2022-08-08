package dev.bomberman.object.finals;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.bomberman.object.ID;
import dev.bomberman.object.Tile;

public final class SolidTile extends Tile{

	private BufferedImage img = loadImage("Tron_Solid_Mark_III.png", Tile.TILE_W, Tile.TILE_H);
	
	public SolidTile(float posX, float posY) {
		super(posX, posY, ID.SolidTile);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int)posX, (int)posY, null);	
		//g.setColor(Color.WHITE);
		///g.drawRect((int)posX, (int)posY, width, height);
	}

}
