import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<>();
	ArrayList<Alien> aliens = new ArrayList<>();
	Random random = new Random();

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
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).getY() > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).getY() < 0) {
				projectiles.get(i).isActive = false;
			}
		}
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

}
