package states;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MenuState {

	private RoundRectangle2D playButton = createButton(400, 150);
	private RoundRectangle2D settingsButton = createButton(400, 250);
	private RoundRectangle2D quitButton = createButton(400, 350);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font titleFont = new Font("arial", Font.BOLD, 50);
		g.setFont(titleFont);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Air Hockey", 475, 100);

		Font buttonFont = new Font("arial", Font.BOLD, 30);
		g.setFont(buttonFont);

		drawButton(g2d, g, "Play Game", 530, 185, Color.LIGHT_GRAY, playButton);
		drawButton(g2d, g, "Settings", 530, 285, Color.GREEN, settingsButton);
		drawButton(g2d, g, "Quit Game", 530, 385, Color.RED, quitButton);
	}

	private RoundRectangle2D createButton(double x, double y) {
		return new RoundRectangle2D.Double(x, y, 400.0, 50.0, 10.0, 10.0);
	}

	private void drawButton(Graphics2D g2d, Graphics g, String buttonLabel, int x, int y, Color color, RoundRectangle2D button) {
		g.setColor(color);
		g.drawString(buttonLabel, x, y);
		g2d.draw(button);
	}
}
