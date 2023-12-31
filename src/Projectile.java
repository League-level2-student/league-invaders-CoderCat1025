import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Projectile extends GameObject{

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		loadImage("Images/bullet.png");
	}

	void update() {
		y-=speed;
		super.update();
	}

	void draw(Graphics g) {
		if (needImage) {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		} else {
			g.drawImage(image, x, y, width, height, null);
		}
	}

	int getY() {
		return y;
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream("Images/bullet.png"));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

}
