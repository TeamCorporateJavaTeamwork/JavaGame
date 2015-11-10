package states;

import Game.GameEngine;
import Game.entities.Player;
import Game.entities.Puck;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class GameState {

	public void render(Graphics g, Player player1, Player player2, Puck puck, SpriteSheet numbers, SpriteSheet alphabet) {

		//drawing game field
		g.drawImage(Assets.background, 180, 80, 800, 600, null);

		//drawing players and puck
		player1.getMallet().renderLeft(g);
		player2.getMallet().renderRight(g);
		puck.render(g);
		g.setColor(Color.red);

		//names background
		g.drawImage(Assets.headerBackground, 180, 0, 802, 105, null);
		//drawing names
		for (int i = 0; i < player1.getCharKeyName().length; i++) {
			g.drawImage(alphabet.crop(player1.getCharKeyName()[i], 0), 215 + i * 30, 40, null);
		}
		for (int i = 0; i < player2.getCharKeyName().length; i++) {
			g.drawImage(alphabet.crop(player2.getCharKeyName()[player2.getCharKeyName().length - 1 - i], 0), 920 - i * 30, 40, null);
		}
		//drawing score
		g.drawImage(alphabet.crop(player1.getScore() + 27, 0), 520, 40, null);
		g.drawImage(alphabet.crop(player2.getScore() + 27, 0), 610, 40, null);
		//g.drawImage(numbers.crop(player2.getScore(), 0), 610, 23, null);
		//drawing timer if necessery;
		if (GameEngine.isShouldCountDown()) {								//left indent + wholeFieldY + digitWidth, top indent + wholeFieldY - exactPos
			g.drawImage(numbers.crop(GameEngine.tasks.countDown.position, 0), 490 + 60, 340 -150, null);
		}
	}
}