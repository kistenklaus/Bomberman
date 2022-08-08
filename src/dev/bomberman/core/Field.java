package dev.bomberman.core;

import java.util.Vector;

import dev.bomberman.object.CollisionObject;
import dev.bomberman.object.Tile;
import dev.bomberman.object.finals.BreakTile;
import dev.bomberman.object.finals.SolidTile;

public class Field{

	private ObjHandler handler;
	
	public Field(ObjHandler handler) {
		this.handler=handler;
	}
	
	
	public void create(int index){
		if(index==0)standart();
	}
	public void standart(){
		int hor = Tile.TILE_HOR+1;	int ver = Tile.TILE_VER+1;
		int h = Tile.TILE_H; int w = Tile.TILE_W;
		Tile[][] out = new Tile[hor][ver];
		for(int x=1;x<hor-1;x++){
			for(int y = 1;y<ver-1;y++){
				out[x][y]=new BreakTile(x*w, y*h);
			}
		}
		
		
		for(int i=0;i<ver-1 ;i++){
			out[i][0] = new SolidTile(i*w, 0);
			out[i][ver-1] = new SolidTile(i*w, (ver-2)*h);
		}
		for(int i=0;i<hor-1 ;i++){
			out[0][i] = new SolidTile(0, i*h);
			out[hor-1][i] = new SolidTile((hor-2)*w,i*h);
		}
		for(int y=2;y<ver;y+=2){
			for(int x=2;x<hor;x+=2){
				out[x][y] = new SolidTile(x*w, y*h);
			}
		}
		out[1][1] = null;
		out[1][2] = null;
		out[2][1] = null;
		
		out[hor-3][ver-3] = null;
		out[hor-4][ver-3] = null;
		out[hor-3][ver-4] = null;
		
		Vector<CollisionObject> objs = new Vector<>();
		for(int x=0;x<out.length;x++)
			for(int y=0;y<out[0].length;y++)
				if(out[x][y]!=null){
					objs.add(out[x][y]);
				}
		handler.addCollObjAll(objs);
	}
}
