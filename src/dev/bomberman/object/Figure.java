package dev.bomberman.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import dev.bomberman.constants.STATS;
import dev.bomberman.core.ObjHandler;

public abstract class Figure extends TickObject{
	/////FINALS/////
	private final float HITBOXRATIO = 0.75f; //dont go higher than 0.9
											//0.9 is a clean Rectangle
											//0.5 makes a circle Hitbox
	private final float HITBOXSCAL = width*0.05f; // 5% of the width is a good value for a not moveing player
	protected final float DRAWRATIO = 0.9f;	//how much smaler the Player gets higher => smaller
	
	private boolean vulnerable = true;
	private int vulTimer;
	private final int VULTIMER = 60; // in ticks
	
	//FINAL RELATIVITYS
	protected final int DRAWDIFF = (int) ((1-DRAWRATIO)*width);
	
	////VARIABLES///
	protected float velX=0, velY=0;
	protected float movespeed = STATS.MOVEMENTSPEED*Tile.TILE_H/60;
	protected Rectangle[] hitbox;
	protected ObjHandler handler;
	protected int health = 1;
	
	///CONSTRUCTOR////
	public Figure(float posX, float posY,float width,float height, ID id,ObjHandler handler) {
		super(posX, posY,width,height, id);
		hitbox = createHitbox();
		positionHitbox(posX,posY);
		this.handler=handler;
		bounding.setBounds((int)(posX+width/6),(int)(posY+height/6),
				bounding.width*2/3, bounding.height*2/3);
	}
	
	////METHODES
	private Rectangle[] createHitbox() {
		Rectangle[] h = new Rectangle[4];
		h[0]= new Rectangle((int)(width*HITBOXRATIO),(int)(height*(1-HITBOXRATIO)/2));
		h[1]= new Rectangle((int)(width*(1-HITBOXRATIO)/2),(int)(height*HITBOXRATIO));
		h[2]= new Rectangle((int)(width*HITBOXRATIO),(int)(height*(1-HITBOXRATIO)/2));
		h[3]= new Rectangle((int)(width*(1-HITBOXRATIO)/2),(int)(height*HITBOXRATIO));
		return h;
	}
	
	private void positionHitbox(float posx, float posy) {
		hitbox[0].setLocation((int)(posx+width*(1-HITBOXRATIO)/2),
								(int)(posy-HITBOXSCAL));
		hitbox[1].setLocation((int)((posx+width-width*(1-HITBOXRATIO)/2)+HITBOXSCAL),
								(int)(posy+height*(1-HITBOXRATIO)/2));
		hitbox[2].setLocation((int)(posx+width*(1-HITBOXRATIO)/2),
								(int)((posy+height-height*(1-HITBOXRATIO)/2)+HITBOXSCAL));
		hitbox[3].setLocation((int) (posx-HITBOXSCAL),
								(int)(posy+height*(1-HITBOXRATIO)/2));
	}

	public void move(){
		if(velX!=0){
			if(velX>0&&!intsect(hitbox[1])){//right
				posX+=velX;
			}else if(velX<0&&!intsect(hitbox[3])){//left
				posX+=velX;
			}
		}
		if(velY!=0){
			if(velY>0&&!intsect(hitbox[2])){
				posY+=velY;
			}else if(velY<0&&!intsect(hitbox[0])){
				posY+=velY;
			}
		}
		positionHitbox(posX, posY);
		bounding.setLocation((int)(posX+width/6),(int)(posY+height/6));
	}
	
	public void dmg(){
		if(vulnerable){
			health--;
			vulnerable = false;
		}
	}
	
	protected void timing(){
		if(!vulnerable){
			vulTimer++;
			if(vulTimer>VULTIMER){
				vulnerable=true;
				vulTimer=0;
			}
		}
	}
	
	protected void checkHp(){
		if(health<=0)
			handler.removeTickObj(this);
	}
	
	public boolean intsect(Rectangle rec){
		List<CollisionObject> temp = handler.collObj;
		for(int i=0;i<temp.size();i++){
			if(rec.intersects(temp.get(i).getBounding())){
				return true;
			}
		}
		return false;
	}
	
	public void drawHitbox(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.draw(hitbox[0]);
		g2d.draw(hitbox[1]);
		g2d.draw(hitbox[2]);
		g2d.draw(hitbox[3]);
	}
	
	public void drawBounding(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.draw(bounding);
	}

	/////SETTERS AND GETTERS ////////
	public float getVelX(){return velX;}
	public float getVelY(){return velY;}
	public void setVelX(float velX){this.velX=velX;}
	public void setVelY(float velY){this.velY=velY;}
}