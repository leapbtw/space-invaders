/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myspc.obj;

/**
 *
 * @author leapbtw
 */
import javax.swing.ImageIcon;
import myspc.SoundPlayer;

public class Bomb2 extends Bomb {
	
	private boolean destoyed;
	
	Bomb2(){}
	Bomb2(int x, int y){
		initBomb(x,y);
	}
	
	public void initBomb(int x, int y) {
		setDestroyed(true);
		this.x=x;
		this.y=y;
		
		var bombImg = "src/images/bomb2.png";
		var ii = new ImageIcon(bombImg);
		
		setImage(ii.getImage());
	}
	public void setDestroyed(boolean b) {
		this.destoyed=b;
	}
	
	public boolean isDestroyed() {
		return destoyed;
	}
}
