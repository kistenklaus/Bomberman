package dev.bomberman.evntmanagement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import dev.bomberman.core.ObjHandler;
import dev.bomberman.object.ControllerUser;
import dev.bomberman.object.TickObject;

public final class KeyEvntHandler implements KeyListener{
	
	private ObjHandler handler;
	
	public KeyEvntHandler(ObjHandler handler) {
		this.handler=handler;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_ESCAPE)System.exit(0);
		List<TickObject> temp = handler.tickObj;
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getID().controlled){
				ControllerUser conObj = (ControllerUser)temp.get(i);
				if(key==KeyEvent.VK_A)conObj.left=true;
				if(key==KeyEvent.VK_D)conObj.right=true;
				if(key==KeyEvent.VK_W)conObj.up=true;
				if(key==KeyEvent.VK_S)conObj.down=true;
				if(key==KeyEvent.VK_SPACE)conObj.spaceEvent();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		List<TickObject> temp = handler.tickObj;
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getID().controlled){
				ControllerUser conObj = (ControllerUser)temp.get(i);
				if(key==KeyEvent.VK_A)conObj.left=false;
				if(key==KeyEvent.VK_D)conObj.right=false;
				if(key==KeyEvent.VK_W)conObj.up=false;
				if(key==KeyEvent.VK_S)conObj.down=false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
