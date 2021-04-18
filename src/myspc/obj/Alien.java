package myspc.obj;

import javax.swing.ImageIcon;

public class Alien extends Sprite {
	
	private Bomb bomb;
	
	public Alien() {}
	
	public Alien(int x, int y) {
		this.x=x;
		this.y=y;
		
		bomb = new Bomb(x,y);
		
		var alienImg = "src\\images\\alien.png";
		var ii = new ImageIcon(alienImg);
	    setImage(ii.getImage());
	}
	
	public void move(int direction) {
		this.x += direction;
	}
	
	public Bomb getBomb() {
		return bomb;
	}
}

 
