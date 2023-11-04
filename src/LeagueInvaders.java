import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;

	public static void main(String[] args) {
		LeagueInvaders invaders = new LeagueInvaders();
		invaders.setup();

	}

	void setup() {
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);
	}

	public LeagueInvaders() {
		frame = new JFrame();
		panel = new GamePanel();
	}

}
