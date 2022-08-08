package dev.bomberman.object;

import java.awt.Rectangle;

public abstract class TickObject extends GameObject{
	
	public abstract void tick();
	
	protected Rectangle bounding ;
	
	public TickObject(float posX, float posY,float width, float height,ID id) {
		super(posX, posY,width,height, id);
		bounding = new Rectangle((int)posX,(int)posY,(int)width,(int)height);
	}
	public Rectangle getBounding(){
		return bounding;
	}
}
