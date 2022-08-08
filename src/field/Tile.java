package field;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Tile {
	
	public static final int TILE_H =(int) (Field.FIELD_H/Field.TILES_VER),
							TILE_W =(int) (Field.FIELD_W/Field.TILES_HOR);
	
	protected final int tposx, tposy;
	
	protected final Rectangle bounding;
	
	protected boolean solid;
	protected boolean breakable;
	protected boolean destroyed=false;
	
	public abstract void draw(Graphics g);
	
	protected Tile(int tx, int ty, boolean s, boolean b){
		tposx=tx;
		tposy=ty;
		bounding = new Rectangle(getGX(), getGY(), TILE_W, TILE_H);
		solid=s;
		breakable=b;
	
	}
	
	public void destroy(){
		destroyed=true;
		solid=false;
	}
	
	
	public int getGX(){
		return tposx*TILE_W;
	}
	public int getGY(){
		return tposy*TILE_H;
	}
	public boolean isSolid(){
		return solid;
	}
	public Rectangle getBounding(){
		return bounding;
	}
	public boolean isBreakable(){
		return breakable;
	}
	
}
