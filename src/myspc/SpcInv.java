package myspc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SpcInv extends JFrame {
    SoundPlayer sound = new SoundPlayer();
	static SpcInv game = new SpcInv();
	static boolean fix = false;
    
	public SpcInv() {
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
		addMouseListener(new MAdapter());
	}
	
	public static void main(String[]args) {
		StartMenu sm = new StartMenu();
		game.add(new StartMenu());
		game.setVisible(true);
		(new Soundtrack()).run();
	}
	
	private class MAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			int mx = 0, my = 0, key = e.getButton();
			String mb = e.getPoint().toString();
			try {
				mx = Integer.parseInt(mb.substring(17, 20));
				my = Integer.parseInt(mb.substring(23, 26));
			} catch (NumberFormatException exc) {} // try-catch per problemi col substring(), ad ogni modo è sotto controllo
			
			if (SpcInv.fix == false && key == 1 && ((mx >= 250 && mx <= 475) && (my >= 550 && my <= 650))) {
				SpcInv.fix = true;
				game.dispose();
				game = new SpcInv();
				Board bd = new Board();
				game.add(new Board());
				game.setVisible(true);
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			int key = e.getButton();
		}
	}
}