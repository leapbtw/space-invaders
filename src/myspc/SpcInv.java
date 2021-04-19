package myspc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class SpcInv extends JFrame {
	static boolean start = false;
    SoundPlayer sound = new SoundPlayer();
	static SpcInv game = new SpcInv();
    
	public SpcInv() {
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
		addMouseListener(new MAdapter());
		//(new Soundtrack()).run();
	}
	
	public static void main(String[]args) {
		StartMenu sm = new StartMenu();
		Board bd = new Board();
		game.add(new StartMenu());
		game.setVisible(true);
	}
	
	private class MAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			int key = e.getButton();
			int mx = e.getXOnScreen();
			int my = e.getYOnScreen();
			if (key == 1 && ((mx >= 875 && mx <= 1065) && (my >= 755 && my <= 835))) {
				System.out.println("prega che parta");
				start = true;
				game.dispose();
				game.add(new Board());
				game.setVisible(true);
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			int key = e.getButton();
			int mx = e.getXOnScreen();
			int my = e.getYOnScreen();
		}
	}
}