package myspc;

import javax.swing.*;

public class SpcInv extends JFrame {
	
    SoundPlayer sound = new SoundPlayer();
    
	public SpcInv() {
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);	
		
		Board bd = new Board();
        add(new Board());
	}
	
	public static void main(String[]args) {
		SpcInv game = new SpcInv();
		game.setVisible(true);
		(new Soundtrack()).run();
	}
}