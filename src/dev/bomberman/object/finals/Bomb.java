package dev.bomberman.object.finals;

import java.awt.Color;
import java.awt.Graphics;

import dev.bomberman.core.ObjHandler;
import dev.bomberman.object.ID;
import dev.bomberman.object.Item;
import dev.bomberman.object.Tile;

public final class Bomb extends Item{
	
	private static float realativwidth = 0.75f;
	private static float realativheight =0.3f;
	private static float bombwidth = Tile.TILE_W*realativwidth;
	private static float bombheight= Tile.TILE_H*realativheight;
	
	private float tileposX,tileposY;
	private float bombtimer=0f;
	private int green =255;
	private int red = 0;
	private Color progressColor = new Color(60, green, 0);
	
	private ObjHandler handler;
	
	public Bomb(float posX, float posY,ObjHandler handler) {
		super(posX+(1-realativwidth)/2*Tile.TILE_W, posY+(1-realativheight)/2*Tile.TILE_H, bombwidth, bombheight, ID.Bomb);
		tileposX=posX;
		tileposY=posY;
		this.handler=handler;
	}

	@Override
	public void tick() {
		bombtimer+=0.01d;
		green = (int)((1-bombtimer)*255);
		if(green<0)green=0;
		red = (int)(bombtimer*255);
		if(red>255)red=255;
		progressColor = new Color(red, green, 0);
		if(bombtimer>=1)boom();
	}
	public void boom() {
		handler.addTickObj(new Explosion(tileposX, tileposY, Tile.TILE_W, Tile.TILE_H,handler,0));
		handler.addTickObj(new Explosion(tileposX, tileposY, Tile.TILE_W, Tile.TILE_H,handler,1));
		handler.addTickObj(new Explosion(tileposX, tileposY, Tile.TILE_W, Tile.TILE_H,handler,2));
		handler.addTickObj(new Explosion(tileposX, tileposY, Tile.TILE_W, Tile.TILE_H,handler,3));
		handler.addTickObj(new Explosion(tileposX, tileposY, Tile.TILE_W, Tile.TILE_H,handler,-1));
		handler.removeTickObj(this);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(progressColor);
		g.fillRect((int)posX, (int)posY, (int)(width*bombtimer), (int)height);
		g.setColor(Color.black);
		g.drawRect((int)posX, (int)posY, (int)width, (int)height);
	}
	
}
