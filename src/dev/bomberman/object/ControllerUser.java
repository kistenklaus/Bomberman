package dev.bomberman.object;

import dev.bomberman.core.ObjHandler;

public abstract class ControllerUser extends Figure{
	
	public boolean right,left,up,down;
	
	public ControllerUser(float posX, float posY,float width,float height, ID id,ObjHandler handler) {
		super(posX, posY,width,height, id, handler);
	}
	
	abstract public void spaceEvent();
	
	public void simpleController(){
		velX=0; velY=0;
		if(right){
			velX=+movespeed;
		}
		if(left ){
			velX=-movespeed;
		}
		if(up   ){
			velY=-movespeed;
		}
		if(down ){
			velY=+movespeed;
		}
	}

}
