package field;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.STATS;

public class Explosion {
	
	private float e_posx, e_posy;
	private float timer;
	private float timerlimit = STATS.BOOMEXISTENCE;
	private Field field;
	private Rectangle[] ex_hitbox;
	private Rectangle[] ex_drawmodel;
	
	Explosion(float x, float y, Field f){
		e_posx=x;
		e_posy=y;
		field=f;
		ex_hitbox=createHitbox();
		ex_drawmodel=createDrawmodle();
	}
	private Rectangle[] createHitbox(){
		Rectangle[] ex = new Rectangle[4];
		//up
		ex[0] = new Rectangle((int)e_posx-Tile.TILE_W/2, (int)e_posy-Tile.TILE_H/2, Tile.TILE_W, Tile.TILE_H);
		while(!field.intersects(ex[0])){
			ex[0].setBounds(ex[0].x, ex[0].y-Tile.TILE_H, ex[0].width, ex[0].height+Tile.TILE_H);
		}
		
		//right
		ex[1] = new Rectangle((int)e_posx-Tile.TILE_W/2, (int)e_posy-Tile.TILE_H/2, Tile.TILE_W, Tile.TILE_H);
		while(!field.intersects(ex[1])){
			ex[1].setBounds(ex[1].x, ex[1].y, ex[1].width+Tile.TILE_W, ex[1].height);
		}
		
		//down
		ex[2] = new Rectangle((int)e_posx-Tile.TILE_W/2, (int)e_posy-Tile.TILE_H/2, Tile.TILE_W, Tile.TILE_H);
		while(!field.intersects(ex[2])){
			ex[2].setBounds(ex[2].x, ex[2].y, ex[2].width, ex[2].height+Tile.TILE_H);
		}
		
		//left
		ex[3] = new Rectangle((int)e_posx-Tile.TILE_W/2, (int)e_posy-Tile.TILE_H/2, Tile.TILE_W, Tile.TILE_H);
		while(!field.intersects(ex[3])){
			ex[3].setBounds(ex[3].x-Tile.TILE_W, ex[3].y, ex[3].width+Tile.TILE_W, ex[3].height);
		}
		return ex;
	}
	
	private Rectangle[] createDrawmodle() {
		Rectangle[] ex = new Rectangle[ex_hitbox.length];
		for(int l=0;l<ex.length; l++){
			ex[l] = new Rectangle(ex_hitbox[l]);
		}
		ex[0].setBounds(ex[0].x, ex[0].y+Tile.TILE_H, ex[0].width, ex[0].height-Tile.TILE_H);
		ex[1].setBounds(ex[1].x, ex[1].y, ex[1].width-Tile.TILE_W, ex[1].height);
		ex[2].setBounds(ex[2].x, ex[2].y, ex[2].width, ex[2].height-Tile.TILE_H);
		ex[3].setBounds(ex[3].x+Tile.TILE_W, ex[3].y, ex[3].width-Tile.TILE_W, ex[3].height);
		return ex;
	}

	
	
	public void draw(Graphics g){
		for (int i = 0; i < ex_drawmodel.length; i++) {
			/*
			g.setColor(Color.ORANGE);
			g.fillRect(ex_hitbox[i].x, ex_hitbox[i].y, ex_hitbox[i].width, ex_hitbox[i].height);
			*/
			g.setColor(Color.RED);
			g.fillRect(ex_drawmodel[i].x, ex_drawmodel[i].y, ex_drawmodel[i].width, ex_drawmodel[i].height);
		}
	}
	
	private float n = 0.005f*0.5f/STATS.BOOMEXISTENCE;
	public void update(double fd){
		timer+=fd;
		if(timer>timerlimit)
			field.done(this);
		if(timer>n){
			for(int i=0;i<4;i++){
				ex_drawmodel[i].setBounds(ex_drawmodel[i].x+1, ex_drawmodel[i].y+1, ex_drawmodel[i].width-2, ex_drawmodel[i].height-2);
			}

			n+=0.005f*0.5f/STATS.BOOMEXISTENCE;
		}
		
	}
	public Rectangle[] getHitbox(){return ex_hitbox;}
	public Rectangle[] getDrawmodel(){return ex_drawmodel;}
}
