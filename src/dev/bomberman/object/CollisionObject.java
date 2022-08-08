package dev.bomberman.object;

import java.awt.Rectangle;

public abstract class CollisionObject extends GameObject{
	
	private Rectangle bounding;
	protected int width, height;
	
	public CollisionObject(float posX, float posY, int width,int  height,ID id) {
		super(posX, posY,width,height, id);
		bounding = new Rectangle((int)posX, (int)posY, width, height);
		this.width=width;
		this.height=height;
	}
	
	public Rectangle getBounding(){return bounding;}

}
