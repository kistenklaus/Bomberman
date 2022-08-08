package dev.bomberman.object;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GameObject {
	
	protected ID id;
	protected float posX, posY;
	protected float width,height;
	
	public abstract void render(Graphics g);
	
	
	public GameObject(float posX, float posY,float width,float height, ID id){
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		this.id=id;
	}
	
	public ID getID(){return id;}
	
	
	public static BufferedImage loadImage(String name,int width,int height){
		BufferedImage gfx;
		BufferedImage scaled=null;
		try {
			gfx= ImageIO.read(GameObject.class.getClassLoader().getResourceAsStream("dev/bomberman/gfx/"+name));
			scaled = new BufferedImage(gfx.getWidth(),gfx.getHeight(),BufferedImage.TYPE_INT_ARGB );
			AffineTransform at = new AffineTransform();
			at.scale((double)width/gfx.getWidth(), (double)height/gfx.getHeight());
			AffineTransformOp scaleOp =
					new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			scaled = scaleOp.filter(gfx, scaled);
			
		} catch (IOException e) {System.out.println("fuck");;}
		
		return scaled;
	}
	
	public static BufferedImage createRotatedCopy(BufferedImage in ,double omega){
		BufferedImage bufferedImage = in;
		
		double theta = omega*Math.PI/180;
		AffineTransform tx = new AffineTransform();
		tx.rotate(theta, Tile.TILE_W / 2, Tile.TILE_H / 2);

		AffineTransformOp op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		bufferedImage = op.filter(bufferedImage, null);
	    return bufferedImage;
	}
}
