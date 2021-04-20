/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myspc.obj;

import javax.swing.ImageIcon;

/**
 *
 * @author Andrei
 */
public class CaricamentoPowerUp extends Sprite {
	
	
	public CaricamentoPowerUp() {
		var wallpaper = "src\\images\\Caricamento.png";
		var ii = new ImageIcon(wallpaper);
	    setImage(ii.getImage());
	}
	
    public boolean isVisible() {
        return true;
    }
	
    public int move(int x) {
		return x += 100; 	
	}
}
