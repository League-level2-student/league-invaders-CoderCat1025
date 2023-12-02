import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed;
	boolean isActive;
	boolean movingUp = false;
	boolean movingDown = false;
	boolean movingLeft = false;
	boolean movingRight = false;
	Rectangle collisionBox;


	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		speed = 0;
		isActive = true;
		collisionBox = new Rectangle(x, y, width, height);
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
}
