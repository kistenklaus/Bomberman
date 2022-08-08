package field;

import java.awt.Color;
import java.awt.Graphics;

import core.STATS;

public class Dynamite {
	
	private float d_posx, d_posy;
	private float timer;
	private float timerlimit = STATS.BOMBTIMER;
	private Field field;
	
	public Dynamite(float x, float y,Field f){		
		
		
		
		
		
		d_posx=Tile.TILE_W*Math.round((x-Tile.TILE_W/2)/Tile.TILE_W)+Tile.TILE_W/2;
		d_posy=Tile.TILE_H*Math.round((y-Tile.TILE_H/2)/Tile.TILE_H)+Tile.TILE_H/2;
		field = f;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.ORANGE);
		g.fillRect((int)d_posx-Tile.TILE_W*3/8, (int)d_posy-Tile.TILE_H/8, (int)((timer/timerlimit)*Tile.TILE_W*3/4), Tile.TILE_H*1/4);
		g.setColor(Color.RED);
		g.drawRect((int)d_posx-Tile.TILE_W*3/8, (int)d_posy-Tile.TILE_H/8, Tile.TILE_W*3/4, Tile.TILE_H*1/4);
	}
	public void update(double fd){
		timer+=fd;
		if(timer>timerlimit){
			field.explodeDynamite(this);
		}
	}
	public float getX(){return d_posx;}
	public float getY(){return d_posy;}
}
