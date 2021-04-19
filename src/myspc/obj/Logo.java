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
public class Logo extends Sprite {
	public Logo() {
		var wallpaper = "src\\images\\logo.png";
		var ii = new ImageIcon(wallpaper);
	    setImage(ii.getImage());
	}
}
