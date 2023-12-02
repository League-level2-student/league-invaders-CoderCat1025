import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font subtitleFont;
	Timer frameDraw;
	Timer alienSpawn;
	Rocketship ship = new Rocketship (250, 700, 50, 50);
	ObjectManager manager = new ObjectManager(ship);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
			drawMenuState(g);
		}else if(currentState == GAME){
			drawGameState(g);
		}else if(currentState == END){
			drawEndState(g);
		}
	}

	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		if (ship.isActive==false) {
			currentState=END;
		}
	}

	void updateEndState() {

	}	

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS",10, 150);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start",120, 500);
		g.drawString("Press SPACE for instructions",80, 600);
	}	

	void drawGameState(Graphics g) {
		{
			g.setColor(Color.BLACK);
			g.fillRect(0,  0,  LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
			loadImage("Images/space.png");
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
			g.setColor(Color.WHITE);
			g.setFont(subtitleFont);
			g.drawString("Score " + String.valueOf(manager.getScore()), 200, 50);
			manager.draw(g);
			ship.move();
		}
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 150);
		g.setFont(subtitleFont);
		g.drawString("Enemies Killed:", 120, 500);
		g.drawString(String.valueOf(manager.getScore()), 325, 500);
		g.drawString("Press ENTER to restart", 110, 600);
	}

	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subtitleFont = new Font("Arial", Font.PLAIN, 25);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(currentState == MENU){
			updateMenuState();
		}
		else if(currentState == GAME){
			updateGameState();
		}
		else if(currentState == END){
			updateEndState();
		}
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
				ship = new Rocketship(250, 700, 50, 50);
				manager = new ObjectManager(ship);
			} else {
				if (currentState == GAME) {
					alienSpawn.stop();
				}
				else if (currentState == MENU) {
					startGame();
				}
				currentState++;
			}
		}

		if (currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W) {
				ship.movingUp = true;
			}

			if (e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
				ship.movingDown = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
				ship.movingLeft = true;
			}

			if (e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) {
				ship.movingRight = true;
			}
			if (e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_ALT) {
				manager.addProjectile(ship.getProjectile());
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent a) {
		if (currentState == GAME) {
			if (a.getKeyCode()==KeyEvent.VK_UP || a.getKeyCode()==KeyEvent.VK_W) {
				ship.movingUp = false;

			}

			if (a.getKeyCode()==KeyEvent.VK_DOWN || a.getKeyCode()==KeyEvent.VK_S) {
				ship.movingDown = false;


			}
			if (a.getKeyCode()==KeyEvent.VK_LEFT || a.getKeyCode()==KeyEvent.VK_A) {
				ship.movingLeft = false;


			}

			if (a.getKeyCode()==KeyEvent.VK_RIGHT || a.getKeyCode()==KeyEvent.VK_D) {
				ship.movingRight = false;

			}


		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream("Images/space.png"));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	void startGame() {
		alienSpawn = new Timer(1000, manager);
		alienSpawn.start();
	}
}
