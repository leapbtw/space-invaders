package myspc.obj;

import java.awt.Image;
import javax.swing.ImageIcon;

public class MenuObject {
	
	int x = 0, y = 0;
	Image image;
	
	public MenuObject(String path) {
		try {
			setImage(new ImageIcon(path).getImage());
		} catch (Exception e) {
			setImage(new ImageIcon("src\\images\\GATTO.png").getImage());
		}
	}
	
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}