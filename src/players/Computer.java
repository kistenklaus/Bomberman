package players;

import java.awt.Color;
import java.awt.Graphics;

import field.Field;
import field.Tile;

public class Computer extends Figure{

	public Computer(int fx, int fy, Field f) {
		super(fx, fy, f,false);
	}

	@Override
	public void drawmodel(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval((int)(char_posx+Tile.TILE_W*0.1), (int)(char_posy+Tile.TILE_H*0.1), (int)(Tile.TILE_W*0.8), (int)(Tile.TILE_W*0.8));
		g.setColor(Color.RED);
		g.drawOval((int)(char_posx+Tile.TILE_W*0.1), (int)(char_posy+Tile.TILE_H*0.1), (int)(Tile.TILE_W*0.8), (int)(Tile.TILE_W*0.8));
	}

	@Override
	public void move(double fd) {
		// TODO Auto-generated method stub
		
	}

}
