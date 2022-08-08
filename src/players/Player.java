package players;

import java.awt.Color;
import java.awt.Graphics;

import core.STATS;
import evnt_managment.KeyEvtManager;
import field.Field;
import field.Tile;

public class Player extends Figure{
	
	public float ms = STATS.MOVEMENTSPEED;
	
	public Player(int px, int py,Field f){
		super(px,py,f,true);
	}	
	@Override
	public void drawmodel(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval((int)(char_posx+Tile.TILE_W*0.1), (int)(char_posy+Tile.TILE_H*0.1), (int)(Tile.TILE_W*0.8), (int)(Tile.TILE_W*0.8));
		g.setColor(Color.CYAN);
		g.drawOval((int)(char_posx+Tile.TILE_W*0.1), (int)(char_posy+Tile.TILE_H*0.1), (int)(Tile.TILE_W*0.8), (int)(Tile.TILE_W*0.8));
		/*
		for (int i = 0; i < figure_bounding.length; i++) {
			g.drawRect(figure_bounding[i].x, figure_bounding[i].y, figure_bounding[i].width, figure_bounding[i].height);
		}
		*/
	}
	@Override
	public void move(double fd) {
		if(KeyEvtManager.down){
			if(!field.intersects(figure_bounding[2]))
				char_posy+=ms*fd;
		}
		if(KeyEvtManager.up){
			if(!field.intersects(figure_bounding[0]))
				char_posy-=ms*fd;
		}
		if(KeyEvtManager.right){
			if(!field.intersects(figure_bounding[1]))
				char_posx+=ms*fd;
		}
		if(KeyEvtManager.left){
			if(!field.intersects(figure_bounding[3]))
				char_posx-=ms*fd;
		}
		
		if(KeyEvtManager.boom){
			field.addDynamite(char_posx+Tile.TILE_W/2, char_posy+Tile.TILE_H/2);
			KeyEvtManager.boom=false;
		}
	}
	
}
