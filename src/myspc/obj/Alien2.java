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
public class Alien2 extends Alien {
	
	private Siluro_Sovietico s;
	
	public Alien2() {}
	
	
	public Alien2(int x, int y) {
		
		this.x=x;
		this.y=y;
		
		s = new Siluro_Sovietico(x,y);
		
		var alienImg = "src/images/alien2.png";
		
	    var ii = new ImageIcon(alienImg);
	    
	    setImage(ii.getImage());
	}
	public void act(int direction) {
		this.x += direction;
	}
	
	public Bomb getBomb() {
		return s ;
	}
}
