package myspc;
import javax.swing.JPanel;
import javax.swing.Timer;

import myspc.obj.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static myspc.SpcInv.game;


public class GameoverMenu extends JPanel {

    private Dimension d;
    private Gameover sfondo = new Gameover();
	private GameoverText text = new GameoverText();
	private Player player = new Player();
	private InsertCoin coin = new InsertCoin();
    
    SoundPlayer sound = new SoundPlayer();

    private Timer timer;

    public GameoverMenu() {
        boardInit();
    }

    private void boardInit() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();
    }
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.drawImage(sfondo.getImage(), 0, 0, this);
		g.drawImage(text.getImage(), 20, 35, this);
		g.drawImage(coin.getImage(), 64, 140, this);
        Toolkit.getDefaultToolkit().sync();
    }
	
    private void doGameCycle() {
        repaint();
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
        }
    }
}