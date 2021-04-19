package myspc.obj;

import javax.swing.ImageIcon;

import myspc.Commons;

import java.awt.event.KeyEvent;
public class Player extends Sprite {
	
	
	private int width;
	
	public Player() {
		initPlayer();
	}
	
	private void initPlayer() {
		
		var playerImg = "src\\images\\player.png";
		var ii = new ImageIcon(playerImg);
		
		width = ii.getImage().getWidth(null);
		setImage(ii.getImage());
		
		//coordinate di spawn del player
		int START_X = 270;
        setX(START_X);
        int START_Y = 250; //280
        setY(START_Y);
	}
	
	public void move() {
		x += dx; //coord + mov
		if (x <= 2) x = 2;  //bordo sinistro
		if (x >= Commons.BOARD_WIDTH - 2 * width) x = Commons.BOARD_WIDTH - 2 * width;  //bordo destro
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) dx = -4;//-2
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) dx = 4;//2
	}
        
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) dx = 0;	
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) dx = 0;
    }
}
