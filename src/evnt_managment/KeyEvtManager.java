package evnt_managment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEvtManager implements KeyListener{
	
	public static boolean left=false, right=false, up=false , down=false;
	public static boolean boom = false;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)System.exit(0);
		if(e.getKeyCode()==KeyEvent.VK_A)left=true;
		if(e.getKeyCode()==KeyEvent.VK_D)right=true;
		if(e.getKeyCode()==KeyEvent.VK_W)up=true;
		if(e.getKeyCode()==KeyEvent.VK_S)down=true;
		if(e.getKeyCode()==KeyEvent.VK_SPACE&&boom==false)boom=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A)left=false;
		if(e.getKeyCode()==KeyEvent.VK_D)right=false;
		if(e.getKeyCode()==KeyEvent.VK_W)up=false;
		if(e.getKeyCode()==KeyEvent.VK_S)down=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
