package dev.bomberman.object.finals;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import dev.bomberman.constants.KONSTANT;
import dev.bomberman.constants.SET;
import dev.bomberman.object.ID;
import dev.bomberman.object.TickObject;
import dev.bomberman.object.Tile;

public class Space extends TickObject{
	
	
	private final int pieceWidth = Tile.TILE_W/8,pieceHeight = Tile.TILE_H;
	
	private FallingObject[] fallObjs;
	private int nTicks = 100;
	private int ticks;
	private Random ran = new Random();
	
	public Space(float posX, float posY, float width, float height, ID id) {
		super(posX, posY, width, height, id);
		fallObjs = createObj(5);
	}
	

	private FallingObject[] createObj(int n) {
		FallingObject[] obj = new FallingObject[n];
		for(int i=0;i<n;i++){
			obj[i] = new FallingObject();
		}
		return obj;
	}


	@Override
	public void tick() {
		ticks++;
		if(ticks>nTicks) {
			for (int i = 0; i < fallObjs.length; i++) {
				fallObjs[i].tick();
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, KONSTANT.GAMEWIDTH, KONSTANT.GAMEHEIGHT);
		for (int i = 0; i < fallObjs.length; i++) {
			fallObjs[i].render(g);
		}
	}
	
	private class FallingObject{
		
		private float y;
		private FallingPiece[] pieces;
		private float velY;
	
		
		private FallingObject(){
			velY=ran.nextInt(SET.SPACEVELY/2)+SET.SPACEVELY;
			y=ran.nextInt(KONSTANT.GAMEHEIGHT)-pieceHeight;
			pieces = createPiece(ran.nextInt(5)+5);
		}
		
		private FallingPiece[] createPiece(int n) {
			FallingPiece[] obj = new FallingPiece[n];
			obj[0] = new FallingPiece(y);
			for(int i=1;i<n;i++){
				obj[i] = new FallingPiece(obj[i-1],i);
			}
			return obj;
		}
		
		private void tick(){
			for (int i = 0; i < pieces.length; i++) {
				pieces[i].tick();
			}
		}
		private void render(Graphics g){
			for (int i = 0; i < pieces.length; i++) {
				pieces[i].render(g);
			}
		}
		
		private class FallingPiece{
			
			private float posX, posY;
			private FallingPiece pre;
			private int index;
			
			private FallingPiece(float posY){
				this.pre=null;
				this.posY=posY;
				this.posX=ran.nextInt(KONSTANT.GAMEWIDTH-pieceWidth);
				this.index = 0;
			}
			private FallingPiece(FallingPiece predecessor,int index){
				pre = predecessor;
				this.index=index;
			}
			
			private void tick(){
				if(index != 0){
					posX=pre.getposX();
					posY=pre.getpoxY()-pieceHeight;
				}if (index ==0){//main
					posY+=velY;
					if(posY-10*pieceHeight>KONSTANT.GAMEHEIGHT){
						posY=-pieceHeight;
						posX=ran.nextInt(KONSTANT.GAMEWIDTH-pieceWidth);
						velY=ran.nextInt(SET.SPACEVELY/2)+SET.SPACEVELY;
								
					}
				}
				
			}
			private void render(Graphics g){
				g.setColor(Color.GRAY);
				g.fillRect((int)posX, (int)posY, (int)(pieceWidth*velY/SET.SPACEVELY), pieceHeight);
			}
			
			private float getposX(){return posX;	}
			private float getpoxY(){return posY;	}
		}
	}
}




















