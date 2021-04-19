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
public class Sfondo extends Sprite {
	int x = 0, y = 0;
	
	public Sfondo() {
		var wallpaper = "src\\images\\sfondo4.gif";
		var ii = new ImageIcon(wallpaper);
	    setImage(ii.getImage());
	}
}
