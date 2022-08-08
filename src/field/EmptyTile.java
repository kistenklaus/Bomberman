package field;

import java.awt.Graphics;

public final class EmptyTile extends Tile{

	public EmptyTile(int tx, int ty){
		super(tx,ty,false,false);
	}

	@Override
	public void draw(Graphics g) {
		//Nothing cause its a Empty Tile
	}
}
