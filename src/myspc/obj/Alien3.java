/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myspc.obj;

import javax.swing.ImageIcon;

/**
 *
 * @author leapbtw
 */
public class Alien3 extends Alien {
	
	
	private Bomb2 bomb;
	
	public Alien3() {}
	
	
	public Alien3(int x, int y) {
		
		this.x=x;
		this.y=y;
		
		bomb = new Bomb2(x,y);
		
		
		var alienImg = "src/images/alien3.png";
		
	    var ii = new ImageIcon(alienImg);
	    
	    setImage(ii.getImage());
		
		
	}
	
	public void act(int direction) {
		this.x += direction;
	}
	
	public Bomb getBomb() {
		return bomb;
	}
}
