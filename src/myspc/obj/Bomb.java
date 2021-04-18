package myspc.obj;

import javax.swing.ImageIcon;
import myspc.SoundPlayer;

public class Bomb extends Sprite{
	
	private boolean destoyed;
	
	Bomb(){}
	Bomb(int x, int y){
		initBomb(x,y);
	}
	
	public void initBomb(int x, int y) {
		setDestroyed(true);
		this.x=x;
		this.y=y;
		
		var bombImg = "src/images/bomb.png";
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