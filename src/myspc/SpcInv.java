package myspc;

import java.awt.event.*;

import javax.swing.*;


public class SpcInv extends JFrame {
    SoundPlayer sound = new SoundPlayer();
	static SpcInv game = new SpcInv();
	static boolean start = false, end = false;
    
	public SpcInv() {
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
		addMouseListener(new MAdapter());
	}

	public static void main(String[]args) {
		StartMenu sm = new StartMenu();
		game.setContentPane(sm);
		game.setVisible(true);
		(new Soundtrack()).run();
		
	}
	
	private class MAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			// prendo le coordinate del mouse DENTRO al JFrame
			int mx = 0, my = 0, key = e.getButton();
			String mb = e.getPoint().toString();
			try {
				mx = Integer.parseInt(mb.substring(17, 20));
				my = Integer.parseInt(mb.substring(23, 26));
			} catch (NumberFormatException exc) {} // try-catch per problemi col substring(), ad ogni modo è sotto controllo
			
			//controllo se il mouse è dentro al bottone start -> Board();
			if (!SpcInv.start && !SpcInv.end && key == 1 && ((mx >= 250 && mx <= 475) && (my >= 550 && my <= 650))) {
				SpcInv.start = true;
				game.removeAll();
				game.dispose();
				game = new SpcInv();
				Board bd = new Board();
				game.setContentPane(bd);
				game.setVisible(true);
			}
			
			//controllo se il mouse è dentro al bottone restart -> Board();
			if (SpcInv.end && !SpcInv.start && key == 1 && ((mx >= 120 && mx <= 335) && (my >= 230 && my <= 430))) {
				SpcInv.start = true;
				game.removeAll();
				game.dispose();
				game = new SpcInv();
				Board bd = new Board();
				game.setContentPane(bd);
				game.setVisible(true);	
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			int key = e.getButton();
		}
	}
}