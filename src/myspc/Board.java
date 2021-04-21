package myspc;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import myspc.obj.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;

import java.util.*;
import static myspc.SpcInv.game;


public class Board extends JPanel {

    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;
    private Timer timer;
    	
    SoundPlayer sound = new SoundPlayer();
    CaricamentoPowerUp barra = new CaricamentoPowerUp();
    MenuObject sfondo = new MenuObject("src\\images\\spazio3.gif");
	
    private boolean PowerupActive = false;
    private int direction = -1;
    private int kills = 0, rx = 0, ry = 640;
    private int NUMBER_OF_ALIENS_TO_DESTROY = 0;
    private boolean inGame = true;
	
	public int score = 0;
	
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
		
		int r1 = new Random().nextInt(16);
		//int r1 = 3;
		if (r1 == 0) {
			for (int x = 0; x < 6; x++) {
			    for (int y = 0; y < 4; y++) {
					int r2 = new Random().nextInt(77);
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                                        if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);                   
					aliens.add((Alien) alien);
			    }
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 24;
		}
		if (r1 == 1) { // 6X4
			for (int x = 0; x < 5; x++) {
			    for (int y = 0; y < 4; y++) {
					int r2 = new Random().nextInt(77);
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                    if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);                   
					if (((x == 1 || x == 3) && y == 0) || (y == 1) || ((x == 1 || x == 2 || x == 3) && y == 2) || (x == 2 && y == 3)) aliens.add((Alien) alien);
			    }
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 11;
		}
		if (r1 == 2) { // LINEA ORIZZONTALE
			for (int x = 0; x < 12; x++) {
                int r2 = new Random().nextInt(77);
					int y = 3;
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * 3);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * 3);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * 3);
                    if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);                   
					aliens.add((Alien) alien);
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 12;
		}
		if (r1 == 3) { // DOPPIO SLASH
			for (int x = 0; x < 7; x++) {
                int r2 = new Random().nextInt(77);
					int y = 0;
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * x);
					if (r2 >= 26 && r2 <= 75) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * x);
                    if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * x);    
					aliens.add((Alien) alien);
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x + 100, Commons.ALIEN_INIT_Y + 25 * x);
					if (r2 >= 26 && r2 <= 75) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x + 100, Commons.ALIEN_INIT_Y + 25 * x);
                    if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x + 100, Commons.ALIEN_INIT_Y + 25 * x);   
					aliens.add((Alien) alien);
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 14;
		}
		if (r1 == 4) { // FEATURE
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++) {
					int r2 = new Random().nextInt(77);
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                    
					if (x == 0 || x == 4 || y == 0 || y == 4) aliens.add((Alien) alien);
				}
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 16;
		}
		if (r1 == 5) { // CROCE DIAGONALE
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    int r2 = new Random().nextInt(20);
					var alien = new Sprite();
					if (r2 <= 18) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 19) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                    if ((x == y) ||((x + y) == 4) ) aliens.add((Alien) alien);
                }
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 9;
        }
		if (r1 == 6) { // LOL
			for (int x = 0; x < 11; x++) {
                for (int y = 0; y < 4; y++) {
					int r2 = new Random().nextInt(77);
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                    if (x == 0 || x == 4 || x == 6 || x == 8) aliens.add((Alien) alien);
					if (x == 1 && y == 3) aliens.add((Alien) alien);
					if (x == 2 && y == 3) aliens.add((Alien) alien);
					if (x == 5 && (y == 0 || y == 3)) aliens.add((Alien) alien);
					if ((x == 9 || x == 10) && y == 3) aliens.add((Alien) alien);
				}
			}
			NUMBER_OF_ALIENS_TO_DESTROY = 22;
		}
		if (r1 == 7) { // CASA
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    int r2 = new Random().nextInt(77);
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                    
                    if ((x == 0 || x == 9) && (y == 2) ) aliens.add((Alien) alien);
                    else if ((x == 1 || x == 8) && (y == 1 || y == 2)) aliens.add((Alien) alien);
                    else if ((x == 2 || x == 7) && (y >= 0 && y <= 6) ) aliens.add((Alien) alien);
                    else if ((x == 3 || x == 6) && (y >= 0 && y <= 3) || (x == 3 || x == 6) && (y == 5 || y == 6) ) aliens.add((Alien) alien);
                    else if ((x == 4 || x == 5) && (y >= 0 && y <= 5)) aliens.add((Alien) alien);
                }
            }
            NUMBER_OF_ALIENS_TO_DESTROY = 44;
        }
		if (r1 == 8) { // SPACE INVADERS
            for (int x = 0; x < 11; x++) {
                for (int y = 0; y < 8; y++) {
                    int r2 = new Random().nextInt(77);
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                    
					if ((x == 2 || x == 8) && y == 0) aliens.add((Alien) alien);
					if ((x == 3 || x == 7) && y == 1) aliens.add((Alien) alien);
					if ((x >= 2 && x <= 8) && y == 2) aliens.add((Alien) alien);
					if (y == 3 && (x == 1 || x == 2 || x == 4 || x == 5 || x == 6 || x == 8 || x == 9)) aliens.add((Alien) alien);
					if (y == 4) aliens.add((Alien) alien);
					if (y == 5 && (x == 0 || x == 10 || (x >= 2 && x <= 8))) aliens.add((Alien) alien);
					if (y == 6 && (x == 0 || x == 2 || x == 8 || x == 10)) aliens.add((Alien) alien);
				}
            }
            NUMBER_OF_ALIENS_TO_DESTROY = 42;
        }
		if (r1 == 9) { // FEATURE 2
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 5; y++) {
                    int r2 = new Random().nextInt(77);
					var alien = new Sprite();
					if (r2 >= 0 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                    
					if ((x == 1) || (x == 4) || (x == 7)) aliens.add((Alien) alien);
                    if ((x == 0) || (x == 3) || (x == 6) && (y == 0) || (y == 2) || (y == 4)) aliens.add((Alien) alien);
                }
            }
            NUMBER_OF_ALIENS_TO_DESTROY = 32;
        }
		if (r1 >= 10) { // RANDOM
			
			for (int x = 0; x < 15; x++) {
				for (int y = 0; y < 7; y++) {
					int r2 = new Random().nextInt(77);
					var alien = new Sprite();
					if (r2 >= 0) alien = new Alien(Commons.ALIEN_INIT_X + 25 * 0, Commons.ALIEN_INIT_Y + 25 * 0);
					if (r2 >= 1 && r2 <= 25) alien = new Alien(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 26 && r2 <= 50) alien = new Alien2(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 >= 51 && r2 <= 75) alien = new Alien3(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
					if (r2 == 76) alien = new PowerUp(Commons.ALIEN_INIT_X + 25 * x, Commons.ALIEN_INIT_Y + 25 * y);
                    
					int r3 = new Random().nextInt(4);
					if (r3 == 0) {
						aliens.add((Alien) alien);
						NUMBER_OF_ALIENS_TO_DESTROY++;
					}             
				}           
			}
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
	
    private void drawRet(Graphics g) {
        if (barra.isVisible() && PowerupActive) g.drawImage(barra.getImage(), rx, ry, this);
    }

    private void drawBombing(Graphics g) {
		Image image = new ImageIcon("src\\images\\NetherStar.gif").getImage();
        for (Alien a : aliens) {
			if(a.getImage()!=image){
				Bomb b = a.getBomb();
				if (!b.isDestroyed()) g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
        }
    }
    
    public void drawScore(Graphics g) {
		Font minecraft = null;
		try {
			minecraft = Font.createFont(Font.TRUETYPE_FONT, new File("src\\fonts\\minecraft.ttf")).deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(minecraft);
		} catch (Exception e) {}
		g.setColor(Color.white);
		g.setFont(minecraft);
		g.drawString("SCORE: " + score, 20, 315);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.drawImage(sfondo.getImage(), 0, 0, this);
		
        if (inGame) {
			g.setColor(Color.gray);
            g.drawLine(0, Commons.GROUND, Commons.BOARD_WIDTH, Commons.GROUND);
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);
            drawRet(g);
            drawScore(g);
        } else {
            timer.stop();
			timer = null;
            gameOver();
		}
        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver() {
        inGame = false;
		game.removeAll();
		game.dispose();
		game = null;
		game = new SpcInv();
		
		SpcInv.end = true;
		SpcInv.start = false;
		GameoverMenu sm = new GameoverMenu();              
		game.setContentPane(sm);
		game.setVisible(true);
                
	}

    private void update() {
        var image = new ImageIcon("src/images/NetherStar.gif").getImage();
          
        if (kills == NUMBER_OF_ALIENS_TO_DESTROY) {
            //inGame = false;
            //timer.stop();
            //message = "Game won!";
			NUMBER_OF_ALIENS_TO_DESTROY = 0;
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
                        if(alien.getImage()==image) PowerupActive = true;
                        alien.setImage(new ImageIcon("src\\images\\explosion.png").getImage());
                        alien.setDying(true);
                        sound.sound("src\\audio\\explosion.wav");
                        kills++;
                        score += 100;
			if(PowerupActive == true){
                            rx = barra.move(rx);
                            if(rx > 650) {
                                rx = 0;
                                PowerupActive = false;
                            }
                        }	
                        //piercing
                        else shot.die();
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
                }
                alien.move(direction);
            }
        }

        // bombs
        var generator = new Random();
        
        for (Alien alien : aliens) {
            if(alien.getImage()!=image){
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
			            player.setImage(new ImageIcon("src\\images\\explosion.png").getImage());
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
                shot = new Shot(x, y, PowerupActive);
            }
        }
    }
}