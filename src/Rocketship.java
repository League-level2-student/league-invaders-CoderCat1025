import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 50;
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

	public void up() {
		y-=speed;
	}

	public void down() {
		y+=speed;
	}

	public void left() {
		x-=speed;
	}

	public void right() {
		x+=speed;
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
}
