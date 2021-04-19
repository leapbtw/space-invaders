package myspc;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import myspc.obj.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;


public class StartMenu extends JPanel {

    private Dimension d;
    private Sfondo sfondo = new Sfondo();
	private Logo logo = new Logo();
	private Player player = new Player();
	private StartButton sb = new StartButton();
    private int kills = 0;
    
    SoundPlayer sound = new SoundPlayer();

    private Timer timer;

    public StartMenu() {
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
        g.drawImage(sfondo.getImage(), sfondo.getX(), sfondo.getY(), this);
		g.drawImage(logo.getImage(), 100, 35, this);
		g.drawImage(sb.getImage(), 250, 500, this);

        Toolkit.getDefaultToolkit().sync();
    }
	
    private void update() {
		
    }

    private void doGameCycle() {
        update();
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