package myspc.obj;
import javax.swing.ImageIcon;
/**
 *
 * @author Boghiu
 */
public class PowerUp extends Alien {
	
	private Siluro_Sovietico s;
	
	public PowerUp() {}
	
	
	public PowerUp(int x, int y) {
		
		this.x=x;
		this.y=y;
		
		s = new Siluro_Sovietico(x,y);
		
		var alienImg = "src/images/NetherStar.gif";
		
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
