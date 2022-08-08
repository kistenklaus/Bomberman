package dev.bomberman.object.finals;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.bomberman.core.ObjHandler;
import dev.bomberman.object.ControllerUser;
import dev.bomberman.object.ID;
import dev.bomberman.object.Tile;

public final class Player extends ControllerUser{
	
	private static BufferedImage[] playermodel = createPlayerModel();

	private byte dir=0;
	
	public Player(float posX, float posY,ObjHandler handler) {
		super(posX, posY,Tile.TILE_W,Tile.TILE_H,ID.Player,handler);
		
	}
	
	private static BufferedImage[] createPlayerModel() {
		BufferedImage[] out = new BufferedImage[4];
		BufferedImage img = loadImage("Tron_Player_Mark_I.png", Tile.TILE_W, Tile.TILE_H);
		out[0] = createRotatedCopy(img,180);
		out[1] = createRotatedCopy(img,270);
		out[2] = img;
		out[3] = createRotatedCopy(img,90);
		return out;
	}

	@Override
	public void spaceEvent() {
		//Rundet die Position auf genau ein Feld sodass die Bombe direkt auf einem Feld liegt
		int x = Math.round(posX/Tile.TILE_W)*Tile.TILE_W;
		int y = Math.round(posY/Tile.TILE_H)*Tile.TILE_W;
		super.handler.addTickObj(new Bomb(x, y,handler));
	}
	
	@Override
	public void tick() {
		simpleController();
		move();
		timing();
		checkHp();
		if(up)dir=0;
		if(right)dir=1;
		if(down)dir=2;
		if(left)dir=3;
		
	}

	@Override
	public void render(Graphics g) {
		BufferedImage img = null;
		if(dir==0){
			img = playermodel[0];
		}else if(dir==1){
			img = playermodel[1];
		}else if(dir==2){
			img = playermodel[2];
		}else if(dir==3){
			img = playermodel[3];
		}
		g.drawImage(img, (int)posX, (int)posY, null);
	}




}
