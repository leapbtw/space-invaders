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
public class InsertCoin extends Sprite {
	public InsertCoin() {
		var wallpaper = "src\\images\\coin.gif";
		var ii = new ImageIcon(wallpaper);
	    setImage(ii.getImage());
	}
}
