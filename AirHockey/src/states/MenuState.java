package states;

import Game.GameEngine;
import gfx.Assets;

import java.awt.*;

public class MenuState {

	public static boolean isOn = true;

	public void render(Graphics g) {
		Font titleFont = new Font("arial", Font.BOLD, 50);
		g.setFont(titleFont);
		g.setColor(Color.LIGHT_GRAY);

		GameEngine.setShouldCountDown(true);

		g.drawImage(Assets.header, 428, 32, 352, 49, null);
		g.drawImage(Assets.headerBackground, 180, 0, 802, 105, null);

		Font buttonFont = new Font("arial", Font.BOLD, 30);
		g.setFont(buttonFont);

		g.drawImage(Assets.playGame, 410, 135, 388, 89, null);
		g.drawImage(Assets.settings, 410, 235, 388, 89, null);
		g.drawImage(Assets.quitGame, 410, 335, 388, 89, null);
	}
}
