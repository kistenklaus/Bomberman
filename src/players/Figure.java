package players;

import java.awt.Graphics;
import java.awt.Rectangle;

import core.STATS;
import field.Field;
import field.Tile;

public abstract class Figure {
	
	protected float char_posx, char_posy;
	protected Rectangle[] figure_bounding;
	protected Rectangle hitbox;
	protected Field field;
	protected float c_velx = 0, c_vely = 0;
	protected boolean alive=true;
	protected boolean playable;
	
	public abstract void drawmodel(Graphics g);
	public abstract void move(double fd);
	
	protected Figure(int fx, int fy,Field f,boolean playb){
		char_posx=fx*Tile.TILE_W;
		char_posy=fy*Tile.TILE_H;
		field=f;
		figure_bounding=createBounding();
		hitbox = new Rectangle((int)(Tile.TILE_W*STATS.HITBOXRATIO), (int)(Tile.TILE_H*STATS.HITBOXRATIO));
		updBounding();
		playable=playb;
	}
	public void update(double fd){
		if(alive){
			move(fd);
			updBounding();
		}
	}
	public void draw(Graphics g){
		if(alive){
			drawmodel(g);
		}
	}
	
	
	private Rectangle[] createBounding() {
		Rectangle[] out = new Rectangle[4];
		out[0]= new Rectangle(Tile.TILE_W*3/4,Tile.TILE_H/8);
		out[1]= new Rectangle(Tile.TILE_W/8, Tile.TILE_H*3/4);
		out[2]= new Rectangle(Tile.TILE_W*3/4,Tile.TILE_H/8);
		out[3]= new Rectangle(Tile.TILE_W/8, Tile.TILE_H*3/4);
		return out;
	}
	protected void updBounding(){
		figure_bounding[0].setLocation((int)char_posx+Tile.TILE_W/8, (int)char_posy);
		figure_bounding[1].setLocation((int)char_posx+Tile.TILE_W*7/8, (int)char_posy+Tile.TILE_W/8);
		figure_bounding[2].setLocation((int)char_posx+Tile.TILE_W/8, (int)char_posy+Tile.TILE_H*7/8);
		figure_bounding[3].setLocation((int)char_posx, (int)char_posy+Tile.TILE_W/8);
		hitbox.setLocation((int)(char_posx+Tile.TILE_W*(1-STATS.HITBOXRATIO)/2), (int)(char_posy+Tile.TILE_W*(1-STATS.HITBOXRATIO)/2));
	}
	public Rectangle[] getBounding(){return figure_bounding;}
	public Rectangle getHitbox(){return hitbox;}
	public boolean isAlive(){return alive;	}
	public void setAlive(boolean in){alive=in;}
	public boolean isPlayable(){return playable;	}
}
