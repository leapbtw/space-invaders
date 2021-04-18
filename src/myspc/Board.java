package myspc;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import myspc.obj.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;


public class Board extends JPanel {

    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;
    
    private int direction = -1;
    private int kills = 0;
	private int NUMBER_OF_ALIENS_TO_DESTROY = 20;
    private boolean inGame = true;
    private String message = "Game Over";
    
    SoundPlayer sound = new SoundPlayer();

    private Timer timer;

    public Board() {
        boardInit();
        spawnMobs();
    }

    private void boardInit() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();
    }
	
    private void spawnMobs() {
        player = new Player();
        shot = new Shot();
		aliens = new ArrayList<>();
		
		//int r1 = new Random().nextInt(3);
		int r1 = 3;
		if (r1 == 0) {
			for (int x = 0; x < 4; x++) {
			    for (int y = 0; y < 6; y++) {
					int r2 = new Random().nextInt(3);
					var alien = new Sprite();
					if (r2 == 0) alien = new Alien(Commons.ALIEN_INIT_X + 25 * y, Commons.ALIEN_INIT_Y + 25 * x);
					if (r2 == 1) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * y, Commons.ALIEN_INIT_Y + 25 * x);
					if (r2 == 2) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * y, Commons.ALIEN_INIT_Y + 25 * x);
					aliens.add((Alien) alien);
			    }
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 24;
		}
		if (r1 == 1) {
			for (int x = 0; x < 5; x++) {
			    for (int y = 0; y < 4; y++) {
					int r2 = new Random().nextInt(3);
					var alien = new Sprite();
					if (r2 == 0) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 1) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 2) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (((x == 1 || x == 3) && y == 0) || (y == 1) || ((x == 1 || x == 2 || x == 3) && y == 2) || (x == 2 && y == 3)) aliens.add((Alien) alien);
			    }
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 11;
		}
		if (r1 == 2) {
			var alien = new Sprite();
			for (int x = 0; x < 12; x++) {
				alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25);
				aliens.add((Alien) alien);
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 12;
		}
		if (r1 == 3) {
			var alien = new Sprite();
			for (int x = 0; x < 7; x++) {
				alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * x);
				aliens.add((Alien) alien);
				alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * -x);
				aliens.add((Alien) alien);
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 14;
		}
    }

    private void drawAliens(Graphics g) {
        for (Alien alien : aliens) {
            if (alien.isVisible()) g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            if (alien.isDying()) alien.die();
        }
    }

    private void drawPlayer(Graphics g) {
        if (player.isVisible()) g.drawImage(player.getImage(), player.getX(), player.getY(), this);

        if (player.isDying()) {
            player.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {
        if (shot.isVisible()) g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
    }

    private void drawBombing(Graphics g) {
        for (Alien a : aliens) {
            Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.gray);

        if (inGame) {
            g.drawLine(0, Commons.GROUND, Commons.BOARD_WIDTH, Commons.GROUND);
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {
            if (timer.isRunning()) timer.stop();
            gameOver(g);
		}
        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_WIDTH / 2);
    }

    private void update() {
        if (kills == NUMBER_OF_ALIENS_TO_DESTROY) {
            //inGame = false;
            //timer.stop();
            //message = "Game won!";
			spawnMobs();
			kills = 0;
        }

        // player
        player.move();

        // shot
        if (shot.isVisible()) {
            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Alien alien : aliens) {
                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {
                        var ii = new ImageIcon("src\\images\\explosion.png");
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        sound.sound("src\\audio\\explosion.wav");
                        kills++;
                        //shot.die();
                    }
                }
            }
            int y = shot.getY();
            y -= 10;

            if (y < 0) shot.die();
			else shot.setY(y);
        }

        // aliens
        for (Alien alien : aliens) {
            int x = alien.getX();
            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {
                direction = -1;
                Iterator<Alien> i1 = aliens.iterator();
                while (i1.hasNext()) {
                    Alien a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {
                direction = 1;
                Iterator<Alien> i2 = aliens.iterator();
                while (i2.hasNext()) {
                    Alien a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        Iterator<Alien> it = aliens.iterator();

        while (it.hasNext()) {
            Alien alien = it.next();
            if (alien.isVisible()) {
                int y = alien.getY();
                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }
                alien.move(direction);
            }
        }

        // bombs
        var generator = new Random();
        
        for (Alien alien : aliens) {
            int shot = generator.nextInt(20); //15
            Bomb bomb = alien.getBomb();

            if (shot == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {
                if(inGame == true) sound.sound("src\\audio\\fireball.wav");
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !bomb.isDestroyed()) {
                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {
                    var ii = new ImageIcon("src\\images\\explosion.png");
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() + 1);
                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) bomb.setDestroyed(true);
            }
        }
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
            player.keyPressed(e);
            int x = player.getX();
            int y = player.getY();
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                sound.sound("src\\audio\\arrow.wav");
                shot = new Shot(x, y);
            }
        }
    }
}