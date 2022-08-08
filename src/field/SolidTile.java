package field;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class SolidTile extends Tile{
	
	private BufferedImage solidtile_look = createLook();
	
	protected SolidTile(int tx, int ty) {
		super(tx, ty, true,false);
	}
	
	private BufferedImage createLook(){
		BufferedImage gfx;
		BufferedImage scaled = null;
		try {
			gfx = ImageIO.read(new File("res/solidtile.png"));
			//gfx= ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/BallsOfSteel.png"));
			scaled = new BufferedImage(gfx.getWidth(),gfx.getHeight(),BufferedImage.TYPE_INT_ARGB );
			AffineTransform at = new AffineTransform();
			at.scale((double)TILE_W/gfx.getWidth(), (double)TILE_H/gfx.getHeight());
			AffineTransformOp scaleOp =
					new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			scaled = scaleOp.filter(gfx, scaled);
			
		} catch (IOException e) {e.printStackTrace();}
		
		return scaled;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(solidtile_look, getGX(), getGY(), null);
	}
	

}
