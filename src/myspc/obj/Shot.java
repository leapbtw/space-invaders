package myspc.obj;

import javax.swing.ImageIcon;

public class Shot extends Sprite {
	
	int V_SPACE = 1; 
	int H_SPACE = 18; 
	
	public Shot() {}
	
	public Shot(int x, int y, boolean isPowerUpActive) {
		if (!isPowerUpActive) setImage(new ImageIcon("src\\images\\shot.png").getImage());
		else setImage(new ImageIcon("src\\images\\shot_plus_plus.png").getImage());
		setX(x + H_SPACE);
		setY(y - V_SPACE);
	}
}
