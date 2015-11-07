package states;

import Game.entities.Mallet;
import Game.entities.Puck;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class GameState {

	public void render(Graphics g, Mallet player1, Mallet player2, Puck puck, SpriteSheet numbers) {
		//drawing game field
		g.drawImage(Assets.background, 180, 80, 800, 600, null);

		//drawing players and puck
		player1.renderBlue(g);
		player2.renderRed(g);
		puck.render(g);

		Font players = new Font("arial", Font.BOLD, 30);
		g.setFont(players);
		g.setColor(Color.LIGHT_GRAY);

		g.drawString(player1.getName(), 200, 65);
		g.drawString(player2.getName(), 800, 65);
		//drawing score
		g.drawImage(numbers.crop(player1.getScore(), 0), 475, 23, null);
		g.drawImage(numbers.crop(player2.getScore(), 0), 625, 23, null);
	}
}
