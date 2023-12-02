import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class ObjectManager implements ActionListener{
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<>();
	ArrayList<Alien> aliens = new ArrayList<>();
	Random random = new Random();
	int score = 0;

	public ObjectManager (Rocketship rocket) {
		this.rocket = rocket;
	}

	void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}

	void update() {
		rocket.update();
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).getY() > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if (projectiles.get(i).getY() < 0) {
				projectiles.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}

	void draw(Graphics g){
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for (int o = 0; o < projectiles.size(); o++) {
			projectiles.get(o).draw(g);
		}
	}

	void purgeObjects() {
		
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();

	}

	void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).collisionBox.intersects(rocket.collisionBox)) {
				aliens.get(i).isActive=false;
				rocket.isActive=false;
			}
			for (int o = 0; o < projectiles.size(); o++) {
				if (aliens.get(i).collisionBox.intersects(projectiles.get(o).collisionBox)) {
					aliens.get(i).isActive=false;
					projectiles.get(o).isActive=false;
					score++;
				}
			}

		}
	}

	public int getScore() {
		return score;
	}
}
