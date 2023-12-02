import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Alien extends GameObject{

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
		loadImage("Images/alien.png");
	}

	void update() {
		y+=speed;
		super.update();
	}

	void draw(Graphics g) {
		if (needImage) {
			g.setColor(Color.YELLOW);
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
				image = ImageIO.read(this.getClass().getResourceAsStream("Images/alien.png"));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}
