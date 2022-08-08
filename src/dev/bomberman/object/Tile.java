package dev.bomberman.object;



import dev.bomberman.core.Window;

public abstract class Tile extends CollisionObject{
	
	public static final int TILE_HOR= 15, TILE_VER=TILE_HOR;	//restart
	public static final int TILE_W=((Window.HEIGHT)/TILE_HOR), TILE_H=TILE_W;
	
	
	public Tile(float posX, float posY, ID id) {
		super(posX, posY, TILE_W, TILE_H, id);
	}

}
