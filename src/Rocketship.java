import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 4;
		loadImage("Images/rocket.png");
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}

	void update() {
		if (movingUp && y > 0) {
			y-=speed;
		}
		if (movingDown && y < 700) {
			y+=speed;
		}
		if (movingLeft && x > 0) {
			x-=speed;
		}
		if (movingRight && x < 450) {
			x+=speed;
		}
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream("Images/rocket.png"));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}
