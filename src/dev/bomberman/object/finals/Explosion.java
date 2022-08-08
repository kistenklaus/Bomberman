package dev.bomberman.object.finals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import dev.bomberman.core.ObjHandler;
import dev.bomberman.object.CollisionObject;
import dev.bomberman.object.Figure;
import dev.bomberman.object.ID;
import dev.bomberman.object.Item;
import dev.bomberman.object.TickObject;
import dev.bomberman.object.Tile;

public class Explosion extends Item{
	
	private static int maxlenght=3;
	private CollisionObject hitCollObj;
	private int green = 0;
	
	private float xdif=width/2, ydif=height/2;
	private boolean expanding = true;
	private float expandtionspeed = 2.5f;	//the speed in witch the explosion expands also effects the time
	private boolean isCentrePeace = false;
	
	private ObjHandler handler;
	
	public Explosion(float posX, float posY, float width, float height,ObjHandler handler,int direction) {
		super(posX, posY, width, height, ID.Explosion);
		this.handler=handler;
		createBounding(direction);
		checkIntersections();
		if(direction==-1){
			isCentrePeace=true;
		}
	}

	private void createBounding(int dir){
		int th = Tile.TILE_H; int tw = Tile.TILE_W;
		bounding = new Rectangle((int)posX,(int)posY,tw, th);
		if(dir==0){
			for(int l=0;l<=maxlenght;l++){
				hitCollObj=getFieldIntersection(bounding);
				if(hitCollObj!=null){
					if(hitCollObj.getID().invulnerable){
						posY+=th;
						height-=th;
					}
					break;
				}
				posY-=th;
				height+=th;
				if(l>=maxlenght){
					posY+=th;
					height-=th;
				}
				bounding.setBounds((int)posX, (int)posY, (int)width, (int)height);
			}
			
		}else if(dir==1){
			for(int l=0;l<=maxlenght;l++){
				hitCollObj=getFieldIntersection(bounding);
				if(hitCollObj!=null){
					if(hitCollObj.getID().invulnerable){
						width-=tw;
					}
					break;
				}
				width+=tw;
				if(l>=maxlenght){
					width-=tw;
				}
				bounding.setBounds((int)posX, (int)posY, (int)width, (int)height);
			}
			
			
		}else if(dir==2){
			for(int l=0;l<=maxlenght;l++){
				hitCollObj=getFieldIntersection(bounding);
				if(hitCollObj!=null){
					if(hitCollObj.getID().invulnerable){
						height-=th;
					}
					break;
				}
				height+=th;
				if(l>=maxlenght){
					height-=th;
				}
				bounding.setBounds((int)posX, (int)posY, (int)width, (int)height);
			}
			
		}else if(dir==3){
			for(int l=0;l<=maxlenght;l++){
				hitCollObj=getFieldIntersection(bounding);
				if(hitCollObj!=null){
					if(hitCollObj.getID().invulnerable){
						posX+=tw;
						width-=tw;
					}
					break;
				}
				posX-=tw;
				width+=tw;
				if(l>=maxlenght){
					posX+=tw;
					width-=tw;
				}
				bounding.setBounds((int)posX, (int)posY, (int)width, (int)height);
			}
			
		}else if(dir==-1){
			
		}
	}
	
	private void checkIntersections() {
		if(hitCollObj!=null){
			if(!hitCollObj.getID().invulnerable){
				handler.removeCollObj(hitCollObj);
			}
		}
		List<TickObject> tickObj = handler.tickObj;
		for(int i=0;i<tickObj.size();i++){
			TickObject temp = tickObj.get(i);
			if(temp.getBounding().intersects(bounding)
					&& !temp.getID().invulnerable){
				Figure fig = (Figure) temp;
				fig.dmg();
			}
		}
		
	}
	
	private CollisionObject getFieldIntersection(Rectangle rec){
		List<CollisionObject> collObj = handler.collObj; 
		for(int i=0;i<collObj.size();i++){
			if(collObj.get(i).getBounding().intersects(rec)){
				return collObj.get(i);
			}
		}
		return null;
	}

	@Override
	public void tick() {
		if(expanding){
			xdif-=expandtionspeed;
			ydif-=expandtionspeed;
			if(xdif<0&&ydif<0){
				expanding = false;
			}
		}else{
			xdif+=expandtionspeed;
			ydif+=expandtionspeed;
			if(xdif>width/2||ydif>height/2)
				handler.removeTickObj(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		//drawBounding(g);
		if(isCentrePeace){
			g.setColor(new Color(255, green, 0));	//255, 0-255 , 0  [red-yellow]
			g.fillRect((int)(posX+xdif/4), (int)(posY+ydif/4), (int)(width-2*xdif/4), (int)(height-2*ydif/4));
		}else{
			g.setColor(new Color(255, green, 0));	//255, 0-255 , 0  [red-yellow]
			g.fillRect((int)(posX+xdif), (int)(posY+ydif), (int)(width-2*xdif), (int)(height-2*ydif));
		}
		
		
		}
	

}
